package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Alumno;
import com.spring.learnsphere.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/listar")
    public List<Alumno> listarAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @GetMapping("/buscar/{id}")
    public Alumno buscarAlumnoPorId(@PathVariable Integer id) {
        return alumnoService.findAlumnoById(id);
    }

    @PostMapping("/crear")
    public Alumno crearAlumno(@RequestBody Alumno alumno) {
        return alumnoService.createAlumno(alumno);
    }

    @PutMapping("/editar/{id}")
    public Alumno editarAlumno(@PathVariable Integer id, @RequestBody Alumno alumno) {
        return alumnoService.updateAlumno(id, alumno);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarAlumno(@PathVariable Integer id) {
        alumnoService.deleteAlumno(id);
    }

}
