package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Falta;
import com.spring.learnsphere.service.FaltaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/faltas")
public class FaltaController {

    private final FaltaService faltaService;

    @GetMapping("/listar")
    public List<Falta> listarFaltas() {
        return faltaService.findAllFaltas();
    }

    @GetMapping("/buscar/{id}")
    public Falta buscarFaltaPorId(@PathVariable Integer id) {
        return faltaService.findFaltaById(id);
    }

    @PostMapping("/crear")
    public Falta crearFalta(@RequestBody Falta falta) {
        return faltaService.createFalta(falta);
    }

    @PutMapping("/editar/{id}")
    public Falta editarFalta(@PathVariable Integer id, @RequestBody Falta falta) {
        return faltaService.updateFalta(id, falta);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarFalta(@PathVariable Integer id) {
        faltaService.deleteFalta(id);
    }

}
