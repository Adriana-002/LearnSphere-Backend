package com.spring.learnsphere.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.learnsphere.model.*;
import com.spring.learnsphere.repository.NotaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> listarTodas() {
        return notaRepository.findAll();
    }

    public Optional<Nota> obtenerPorId(Integer id) {
        return notaRepository.findById(id);
    }

    public List<Nota> listarPorAlumno(Integer alumnoId) {
        return notaRepository.findByAlumnoId(alumnoId);
    }

    //Este metodo lo he hecho para que no haya duplicados. si veis que falla lo modificamos
    public Nota guardar(Nota nota) {

        if (notaRepository.existsByAlumnoIdAndCursoAsignaturaIdAndTrimestre(
                nota.getAlumno().getId(), nota.getCursoAsignatura().getId(), nota.getTrimestre())) {
            throw new RuntimeException("Ya existe una nota para este alumno");
        }
        if (nota.getFechaRegistro() == null) {
            nota.setFechaRegistro(LocalDate.now());
        }
        return notaRepository.save(nota);
    }

    //Si cambiamos cursosginatura puede dar error
    public Nota actualizar(Integer id, Nota notaActualizada) {
        return notaRepository.findById(id)
                .map(existente -> {

                    existente.setCalificacion(notaActualizada.getCalificacion());
                    existente.setTrimestre(notaActualizada.getTrimestre());
                    return notaRepository.save(existente);
                })
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    }

    public void eliminar(Integer id) {
        notaRepository.deleteById(id);
    }
}
