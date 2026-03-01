package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.CursoDTO;
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
    public List<CursoDTO> listar() {
        return cursoService.getAll();
    }

    @GetMapping("/buscar/{id}")
    public CursoDTO buscarPorId(@PathVariable Integer id) {
        return cursoService.findById(id);
    }

    @GetMapping("/alumno/{alumnoId}")
    public CursoDTO getByAlumno(@PathVariable Integer alumnoId) {
        return cursoService.getByAlumno(alumnoId);
    }

    @PostMapping("/crear")
    public CursoDTO crear(@RequestBody CursoDTO dto) {
        return cursoService.create(dto);
    }

    @PutMapping("/editar/{id}")
    public CursoDTO editar(@PathVariable Integer id, @RequestBody CursoDTO dto) {
        return cursoService.update(id, dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        cursoService.delete(id);
    }

}
