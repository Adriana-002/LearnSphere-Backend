package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Asignatura;
import com.spring.learnsphere.repository.AsignaturaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Asignatura findAsignaturaById(Integer id) {
        return asignaturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura updateAsignatura(Integer id, Asignatura asignaturaDetails) {
        Asignatura asignatura = findAsignaturaById(id);

        if (asignatura == null) {
            throw new IllegalArgumentException("Asignatura no encontrada");
        }

        asignatura.setNombre(asignaturaDetails.getNombre());
        return createAsignatura(asignatura);
    }

    public void deleteAsignatura(Integer id) {
        if (findAsignaturaById(id) == null) {
            throw new IllegalArgumentException("Asignatura no encontrada");
        }
        asignaturaRepository.deleteById(id);
    }

}
