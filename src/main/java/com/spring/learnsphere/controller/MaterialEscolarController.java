package com.spring.learnsphere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.learnsphere.model.MaterialEscolar;
import com.spring.learnsphere.service.MaterialEscolarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/materiales-escolares")
public class MaterialEscolarController {
    @Autowired
    private MaterialEscolarService service;


    @GetMapping
    public ResponseEntity<List<MaterialEscolar>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialEscolar> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<MaterialEscolar> crear(@RequestBody MaterialEscolar material) {
        MaterialEscolar nuevo = service.guardar(material);
        return ResponseEntity.ok(nuevo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MaterialEscolar> modificar(@PathVariable Integer id, @RequestBody MaterialEscolar material) {
        return service.obtenerPorId(id)
                .map(existente -> {
                    existente.setNombre(material.getNombre());
                    // Las relaciones con cursos se manejarían aparte
                    service.guardar(existente);
                    return ResponseEntity.ok(existente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar (PROFESORADO y ADMINS)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
