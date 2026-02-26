package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Asignatura;
import com.spring.learnsphere.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @GetMapping("/listar")
    public List<Asignatura> listarAsignaturas() {
        return asignaturaService.getAllAsignaturas();
    }

    @GetMapping("/buscar/{id}")
    public Asignatura buscarAsignaturaPorId(@PathVariable Integer id) {
        return asignaturaService.findAsignaturaById(id);
    }

    @PostMapping("/crear")
    public Asignatura crearAsignatura(@RequestBody Asignatura alumno) {
        return asignaturaService.createAsignatura(alumno);
    }

    @PutMapping("/editar/{id}")
    public Asignatura editarAsignatura(@PathVariable Integer id, @RequestBody Asignatura alumno) {
        return asignaturaService.updateAsignatura(id, alumno);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarAsignatura(@PathVariable Integer id) {
        asignaturaService.deleteAsignatura(id);
    }

}
