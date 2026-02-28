package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Falta;
import com.spring.learnsphere.repository.FaltaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FaltaService {

    private final FaltaRepository faltaRepository;

    public List<Falta> findAllFaltas() {
        return faltaRepository.findAll();
    }

    public Falta findFaltaById(Integer id) {
        return faltaRepository.findById(id).orElseThrow(() -> new RuntimeException("Falta no encontrada"));
    }

    public Falta createFalta(Falta falta) {
        return faltaRepository.save(falta);
    }

    public Falta updateFalta(Integer id, Falta faltaDetails) {
        Falta falta = findFaltaById(id);

        if (falta == null) {
            throw new IllegalArgumentException("Falta no encontrada");
        }
        falta.setAlumno(faltaDetails.getAlumno());
        falta.setFecha(faltaDetails.getFecha());
        falta.setHora(faltaDetails.getHora());
        return createFalta(falta);
    }

    public void deleteFalta(Integer id) {
        if (findFaltaById(id) == null) {
            throw new IllegalArgumentException("Falta no encontrada");
        }
        faltaRepository.deleteById(id);
    }

}
