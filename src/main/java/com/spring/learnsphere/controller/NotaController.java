package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.NotaDTO;
import com.spring.learnsphere.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService notaService;

    @GetMapping("/alumno/{alumnoId}/trimestre/{trimestre}")
    public List<NotaDTO> getByAlumnoYTrimestre(@PathVariable Integer alumnoId, @PathVariable Integer trimestre) {
        return notaService.getByAlumnoYTrimestre(alumnoId, trimestre);
    }

    @GetMapping("/alumno/{alumnoId}")
    public List<NotaDTO> getByAlumno(@PathVariable Integer alumnoId) {
        return notaService.getByAlumno(alumnoId);
    }

    @PostMapping("/crear")
    public NotaDTO createNota(@RequestBody NotaDTO dto) {
        return notaService.createNota(dto);
    }

    @PutMapping("/editar/{id}")
    public NotaDTO updateNota(@PathVariable Integer id, @RequestBody NotaDTO dto) {
        return notaService.updateNota(id, dto);
    }

}
