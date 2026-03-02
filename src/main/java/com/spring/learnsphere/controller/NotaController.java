package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.NotaDTO;
import com.spring.learnsphere.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con las notas en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación y actualización de calificaciones,
 * incluyendo el filtrado por alumno y trimestre.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notas")
public class NotaController {

    /** Servicio que contiene la lógica de negocio de notas. */
    private final NotaService notaService;

    /**
     * Obtiene las notas de un alumno en un trimestre específico.
     *
     * @param alumnoId  identificador del alumno
     * @param trimestre número del trimestre
     * @return lista de DTOs con las notas del alumno en el trimestre indicado
     */
    @GetMapping("/alumno/{alumnoId}/trimestre/{trimestre}")
    public List<NotaDTO> getByAlumnoYTrimestre(@PathVariable Integer alumnoId, @PathVariable Integer trimestre) {
        return notaService.getByAlumnoYTrimestre(alumnoId, trimestre);
    }

    /**
     * Obtiene todas las notas de un alumno.
     *
     * @param alumnoId identificador del alumno
     * @return lista de DTOs con todas las notas del alumno
     */
    @GetMapping("/alumno/{alumnoId}")
    public List<NotaDTO> getByAlumno(@PathVariable Integer alumnoId) {
        return notaService.getByAlumno(alumnoId);
    }

    /**
     * Crea una nueva nota para un alumno.
     *
     * @param dto datos de la nota a crear
     * @return DTO con la información de la nota creada
     */
    @PostMapping("/crear")
    public NotaDTO createNota(@RequestBody NotaDTO dto) {
        return notaService.createNota(dto);
    }

    /**
     * Actualiza la calificación de una nota existente.
     *
     * @param id  identificador de la nota a actualizar
     * @param dto datos actualizados de la nota
     * @return DTO con la información de la nota actualizada
     */
    @PutMapping("/editar/{id}")
    public NotaDTO updateNota(@PathVariable Integer id, @RequestBody NotaDTO dto) {
        return notaService.updateNota(id, dto);
    }

}
