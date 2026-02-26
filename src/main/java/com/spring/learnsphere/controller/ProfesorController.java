package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Profesor;
import com.spring.learnsphere.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping("/listar")
    public List<Profesor> listarProfesores() {
        return profesorService.findAllProfesores();
    }

    @GetMapping("/buscar/{id}")
    public Profesor buscarProfesorPorId(@PathVariable Integer id) {
        return profesorService.findProfesorById(id);
    }

    @PostMapping("/crear")
    public Profesor crearProfesor(@RequestBody Profesor profesor) {
        return profesorService.createProfesor(profesor);
    }

    @PutMapping("/editar/{id}")
    public Profesor editarProfesor(@PathVariable Integer id, @RequestBody Profesor profesor) {
        return profesorService.updateProfesor(id, profesor);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProfesor(@PathVariable Integer id) {
        profesorService.deleteProfesor(id);
    }

}
