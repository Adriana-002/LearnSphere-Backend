package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.MaterialEscolarDTO;
import com.spring.learnsphere.service.MaterialEscolarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/material")
public class MaterialEscolarController {

    private final MaterialEscolarService materialService;

    @GetMapping("/listar")
    public List<MaterialEscolarDTO> getAll() {
        return materialService.getAll();
    }

    @GetMapping("/curso/{cursoId}")
    public List<MaterialEscolarDTO> getByCurso(@PathVariable Integer cursoId) {
        return materialService.getByCurso(cursoId);
    }

    @GetMapping("/profesor/{profesorId}")
    public List<MaterialEscolarDTO> getByProfesor(@PathVariable Integer profesorId) {
        return materialService.getByProfesor(profesorId);
    }

    @PostMapping("/crear")
    public MaterialEscolarDTO createMaterial(@RequestBody MaterialEscolarDTO dto) {
        return materialService.createMaterial(dto);
    }


}
