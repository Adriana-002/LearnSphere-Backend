package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.FaltaDTO;
import com.spring.learnsphere.service.FaltaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/faltas")
public class FaltaController {

    private final FaltaService faltaService;

    @GetMapping("/alumno/{alumnoId}")
    public List<FaltaDTO> getByAlumno(@PathVariable Integer alumnoId) {
        return faltaService.getByAlumno(alumnoId);
    }

    @PostMapping("/crear")
    public FaltaDTO createFalta(@RequestBody FaltaDTO dto) {
        return faltaService.createFalta(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteFalta(@PathVariable Integer id) {
        faltaService.deleteFalta(id);
    }

}
