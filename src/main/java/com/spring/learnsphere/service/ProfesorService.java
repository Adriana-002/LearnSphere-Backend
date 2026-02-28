package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Profesor;
import com.spring.learnsphere.repository.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerProfesorPorId(Integer id) {
        return profesorRepository.findById(id);
    }

    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void eliminarProfesor(Integer id) {
        profesorRepository.deleteById(id);
    }
}
