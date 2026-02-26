package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Aviso;
import com.spring.learnsphere.service.AvisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/avisos")
public class AvisoController {

    private final AvisoService avisoService;

    @GetMapping("/listar")
    public List<Aviso> listarAvisos() {
        return avisoService.findAllAvisos();
    }

    @GetMapping("/buscar/{id}")
    public Aviso buscarAvisoPorId(@PathVariable Integer id) {
        return avisoService.findAvisoById(id);
    }

    @PostMapping("/crear")
    public Aviso crearAviso(@RequestBody Aviso aviso) {
        return avisoService.createAviso(aviso);
    }

    @PutMapping("/editar/{id}")
    public Aviso editarAviso(@PathVariable Integer id, @RequestBody Aviso aviso) {
        return avisoService.updateAviso(id, aviso);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarAviso(@PathVariable Integer id) {
        avisoService.deleteAviso(id);
    }

}
