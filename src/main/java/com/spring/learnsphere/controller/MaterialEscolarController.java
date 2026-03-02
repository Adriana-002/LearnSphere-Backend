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

    @PutMapping("/editar/{materialId}")
    public MaterialEscolarDTO updateMaterial(@PathVariable Integer materialId, @RequestBody MaterialEscolarDTO dto) {
        return materialService.updateMaterial(materialId, dto);
    }

    @DeleteMapping("/eliminar/{materialId}")
    public void deleteMaterial(@PathVariable Integer materialId) {
        materialService.deleteMaterial(materialId);
    }


}
