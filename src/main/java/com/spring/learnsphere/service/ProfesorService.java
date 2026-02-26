package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Profesor;
import com.spring.learnsphere.repository.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public List<Profesor> findAllProfesores() {
        return profesorRepository.findAll();
    }

    public Profesor findProfesorById(Integer id) {
        return profesorRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    public Profesor createProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Profesor updateProfesor(Integer id, Profesor profesorDetails) {
        Profesor profesor = findProfesorById(id);

        if (profesor == null) {
            throw new IllegalArgumentException("Profesor no encontrado");
        }
        profesor.setUsuarios(profesorDetails.getUsuarios());
        profesor.setDepartamento(profesorDetails.getDepartamento());
        return createProfesor(profesor);
    }

    public void deleteProfesor(Integer id) {
        if (findProfesorById(id) == null) {
            throw new IllegalArgumentException("Profesor no encontrado");
        }
        profesorRepository.deleteById(id);
    }

}
