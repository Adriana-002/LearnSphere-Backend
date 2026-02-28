package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Nota;
import com.spring.learnsphere.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService notaService;

    @GetMapping("/listar")
    public List<Nota> listarNotas() {
        return notaService.findAllNotas();
    }

    @GetMapping("/buscar/{id}")
    public Nota buscarNotaPorId(@PathVariable Integer id) {
        return notaService.findNotaById(id);
    }

    @PostMapping("/crear")
    public Nota crearNota(@RequestBody Nota nota) {
        return notaService.createNota(nota);
    }

    @PutMapping("/editar/{id}")
    public Nota editarNota(@PathVariable Integer id, @RequestBody Nota nota) {
        return notaService.updateNota(id, nota);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarNota(@PathVariable Integer id) {
        notaService.deleteNota(id);
    }

}
