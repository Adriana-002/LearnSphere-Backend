package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Curso;
import com.spring.learnsphere.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("/listar")
    public List<Curso> listarCursos() {
        return cursoService.findAllCursos();
    }

    @GetMapping("/buscar/{id}")
    public Curso buscarCursoPorId(@PathVariable Integer id) {
        return cursoService.findCursoById(id);
    }

    @PostMapping("/crear")
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.createCurso(curso);
    }

    @PutMapping("/editar/{id}")
    public Curso editarCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        return cursoService.updateCurso(id, curso);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCurso(@PathVariable Integer id) {
        cursoService.deleteCurso(id);
    }

}
