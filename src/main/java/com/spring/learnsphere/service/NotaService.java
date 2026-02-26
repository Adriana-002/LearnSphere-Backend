package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Nota;
import com.spring.learnsphere.repository.NotaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;

    public List<Nota> findAllNotas() {
        return notaRepository.findAll();
    }

    public Nota findNotaById(Integer id) {
        return notaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    }

    public Nota createNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota updateNota(Integer id, Nota notaDetails) {
        Nota nota = findNotaById(id);

        if (nota == null) {
            throw new IllegalArgumentException("Nota no encontrada");
        }
        nota.setAlumno(notaDetails.getAlumno());
        nota.setCursoAsignatura(notaDetails.getCursoAsignatura());
        nota.setTrimestre(notaDetails.getTrimestre());
        nota.setCalificacion(notaDetails.getCalificacion());
        nota.setFechaRegistro(notaDetails.getFechaRegistro());
        return createNota(nota);
    }

    public void deleteNota(Integer id) {
        if (findNotaById(id) == null) {
            throw new IllegalArgumentException("Nota no encontrada");
        }
        notaRepository.deleteById(id);
    }

}
