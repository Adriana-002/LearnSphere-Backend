package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Alumno;
import com.spring.learnsphere.repository.AlumnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoService {
    @Autowired
    private final AlumnoRepository alumnoRepository;

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }
}
