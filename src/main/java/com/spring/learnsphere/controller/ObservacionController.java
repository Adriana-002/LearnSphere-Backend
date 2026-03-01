package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.ObservacionDTO;
import com.spring.learnsphere.service.ObservacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/observaciones")
public class ObservacionController {

    private final ObservacionService observacionService;

    @GetMapping("/alumno/{alumnoId}")
    public List<ObservacionDTO> getByAlumno(@PathVariable Integer alumnoId) {
        return observacionService.getByAlumno(alumnoId);
    }

    @PostMapping("/crear")
    public ObservacionDTO createObservacion(@RequestBody ObservacionDTO dto) {
        return observacionService.createObservacion(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteObservacion(@PathVariable Integer id) {
        observacionService.deleteObservacion(id);
    }

}
