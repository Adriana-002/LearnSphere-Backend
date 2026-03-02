package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.ObservacionDTO;
import com.spring.learnsphere.service.ObservacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con las observaciones en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación y eliminación de observaciones sobre alumnos.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/observaciones")
public class ObservacionController {

    /** Servicio que contiene la lógica de negocio de observaciones. */
    private final ObservacionService observacionService;

    /**
     * Obtiene la lista de observaciones de un alumno.
     *
     * @param alumnoId identificador del alumno
     * @return lista de DTOs con la información de las observaciones del alumno
     */
    @GetMapping("/alumno/{alumnoId}")
    public List<ObservacionDTO> getByAlumno(@PathVariable Integer alumnoId) {
        return observacionService.getByAlumno(alumnoId);
    }

    /**
     * Crea una nueva observación sobre un alumno.
     *
     * @param dto datos de la observación a crear
     * @return DTO con la información de la observación creada
     */
    @PostMapping("/crear")
    public ObservacionDTO createObservacion(@RequestBody ObservacionDTO dto) {
        return observacionService.createObservacion(dto);
    }

    /**
     * Elimina una observación del sistema.
     *
     * @param id identificador de la observación a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public void deleteObservacion(@PathVariable Integer id) {
        observacionService.deleteObservacion(id);
    }

}
