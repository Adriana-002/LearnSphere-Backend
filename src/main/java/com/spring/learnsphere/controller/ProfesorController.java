package com.spring.learnsphere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.learnsphere.model.Profesor;
import com.spring.learnsphere.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profesores")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<Profesor>> listar() {
        return ResponseEntity.ok(profesorService.listarProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtener(@PathVariable Integer id) {
        return profesorService.obtenerProfesorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profesor> crear(@RequestBody Profesor profesor) {

        return ResponseEntity.ok(profesorService.guardarProfesor(profesor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable Integer id, @RequestBody Profesor profesor) {
        return profesorService.obtenerProfesorPorId(id)
                .map(existente -> {
                    existente.setDepartamento(profesor.getDepartamento());

                    profesorService.guardarProfesor(existente);
                    return ResponseEntity.ok(existente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (profesorService.obtenerProfesorPorId(id).isPresent()) {
            profesorService.eliminarProfesor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
