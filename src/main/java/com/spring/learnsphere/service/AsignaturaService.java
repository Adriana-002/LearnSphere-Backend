package com.spring.learnsphere.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.spring.learnsphere.model.Asignatura;
import com.spring.learnsphere.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AsignaturaService {
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<Asignatura> listarTodas() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> obtenerPorId(Integer id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura guardar(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    //Quitar el runTime si veis que da error
    public Asignatura actualizar(Integer id, Asignatura asignaturaActualizada) {
        return asignaturaRepository.findById(id)
                .map(existente -> {
                    existente.setNombre(asignaturaActualizada.getNombre());
                    return asignaturaRepository.save(existente);
                })
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public void eliminar(Integer id) {
        asignaturaRepository.deleteById(id);
    }
}
