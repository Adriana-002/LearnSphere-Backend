package com.spring.learnsphere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.learnsphere.model.Asignatura;
import com.spring.learnsphere.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asignaturas")
public class AsignaturaController {
    @Autowired
    private AsignaturaService service;


    @GetMapping
    public ResponseEntity<List<Asignatura>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Asignatura> crear(@RequestBody Asignatura asignatura) {
        Asignatura nueva = service.guardar(asignatura);
        return ResponseEntity.ok(nueva);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> modificar(@PathVariable Integer id, @RequestBody Asignatura asignatura) {
        try {
            Asignatura actualizada = service.actualizar(id, asignatura);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
