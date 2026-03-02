package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.FaltaDTO;
import com.spring.learnsphere.service.FaltaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con las faltas de asistencia en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación y eliminación de faltas de asistencia.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/faltas")
public class FaltaController {

    /** Servicio que contiene la lógica de negocio de faltas. */
    private final FaltaService faltaService;

    /**
     * Obtiene la lista de faltas de un alumno.
     *
     * @param alumnoId identificador del alumno
     * @return lista de DTOs con la información de las faltas del alumno
     */
    @GetMapping("/alumno/{alumnoId}")
    public List<FaltaDTO> getByAlumno(@PathVariable Integer alumnoId) {
        return faltaService.getByAlumno(alumnoId);
    }

    /**
     * Crea una nueva falta de asistencia.
     *
     * @param dto datos de la falta a crear
     * @return DTO con la información de la falta creada
     */
    @PostMapping("/crear")
    public FaltaDTO createFalta(@RequestBody FaltaDTO dto) {
        return faltaService.createFalta(dto);
    }

    /**
     * Elimina una falta de asistencia del sistema.
     *
     * @param id identificador de la falta a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public void deleteFalta(@PathVariable Integer id) {
        faltaService.deleteFalta(id);
    }

}
