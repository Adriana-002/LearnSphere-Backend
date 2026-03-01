package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AsignaturaDTO;
import com.spring.learnsphere.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @GetMapping("/curso/{cursoId}")
    public List<AsignaturaDTO> getByCurso(@PathVariable Integer cursoId) {
        return asignaturaService.getByCurso(cursoId);
    }

    @GetMapping("/profesor/{profesorId}")
    public List<AsignaturaDTO> getByProfesor(@PathVariable Integer profesorId) {
        return asignaturaService.getByProfesor(profesorId);
    }

    @PostMapping("/crear")
    public AsignaturaDTO createAsignatura(@RequestBody AsignaturaDTO dto) {
        return asignaturaService.createAsignatura(dto);
    }

    @PutMapping("/editar/{id}")
    public AsignaturaDTO updateAsignatura(@PathVariable Integer id, @RequestBody AsignaturaDTO dto) {
        return asignaturaService.updateAsignatura(id, dto);
    }
}
