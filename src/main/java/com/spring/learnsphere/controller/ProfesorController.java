package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.ProfesorDTO;
import com.spring.learnsphere.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping("/listar")
    public List<ProfesorDTO> listar() {
        return profesorService.getAll();
    }

    @GetMapping("/buscar/{id}")
    public ProfesorDTO buscarPorId(@PathVariable Integer id) {
        return profesorService.findById(id);
    }

    @PutMapping("/editar/{id}")
    public ProfesorDTO editar(@PathVariable Integer id, @RequestBody ProfesorDTO dto) {
        return profesorService.update(id, dto);
    }

}
