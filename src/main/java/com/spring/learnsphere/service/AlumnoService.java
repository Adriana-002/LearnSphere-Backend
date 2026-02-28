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

    private final AlumnoRepository alumnoRepository;

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno findAlumnoById(Integer id) {
        return alumnoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    public Alumno createAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno updateAlumno(Integer id, Alumno alumnoDetails) {
        Alumno alumno = findAlumnoById(id);

        if (alumno == null) {
            throw new IllegalArgumentException("Alumno no encontrado");
        }
        alumno.setNombre(alumnoDetails.getNombre());
        alumno.setApellidos(alumnoDetails.getApellidos());
        alumno.setFechaNacimiento(alumnoDetails.getFechaNacimiento());
        alumno.setFotoUrl(alumnoDetails.getFotoUrl());
        return createAlumno(alumno);
    }

    public void deleteAlumno(Integer id) {
        if (findAlumnoById(id) == null) {
            throw new IllegalArgumentException("Alumno no encontrado");
        }
        alumnoRepository.deleteById(id);
    }

}
