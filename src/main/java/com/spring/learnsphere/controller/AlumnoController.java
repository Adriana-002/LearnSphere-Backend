package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Alumno;
import com.spring.learnsphere.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alumnos")
public class AlumnoController {
    @Autowired
    private final AlumnoService alumnoService;

    @GetMapping("/listar")
    public List<Alumno> listarAlumnos() {
        return alumnoService.getAllAlumnos();
    }
}
