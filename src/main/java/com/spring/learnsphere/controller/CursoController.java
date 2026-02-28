package com.spring.learnsphere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.learnsphere.model.Curso;
import com.spring.learnsphere.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;


    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> lista = cursoService.listarCursos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Integer id) {
        return cursoService.obtenerCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevo = cursoService.guardarCurso(curso);
        return ResponseEntity.ok(nuevo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        return cursoService.obtenerCursoPorId(id)
                .map(existente -> {
                    existente.setNombre(curso.getNombre());
                    existente.setAñoAcademico(curso.getAñoAcademico());
                    cursoService.guardarCurso(existente);
                    return ResponseEntity.ok(existente);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
        if (cursoService.obtenerCursoPorId(id).isPresent()) {
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarPorAño(@RequestParam String año) {
        List<Curso> lista = cursoService.listarPorAñoAcademico(año);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }
}
