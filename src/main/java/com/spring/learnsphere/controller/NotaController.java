package com.spring.learnsphere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.learnsphere.model.Nota;
import com.spring.learnsphere.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaService service;


    @GetMapping
    public ResponseEntity<List<Nota>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Nota>> listarPorAlumno(@PathVariable Integer alumnoId) {
        return ResponseEntity.ok(service.listarPorAlumno(alumnoId));
    }


    @PostMapping
    public ResponseEntity<Nota> crear(@RequestBody Nota nota) {
        try {
            Nota nueva = service.guardar(nota);
            return ResponseEntity.ok(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Nota> modificar(@PathVariable Integer id, @RequestBody Nota nota) {
        try {
            Nota actualizada = service.actualizar(id, nota);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar (ADMINS)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
