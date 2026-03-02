package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AsignaturaDTO;
import com.spring.learnsphere.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con las asignaturas en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación y actualización de asignaturas,
 * incluyendo búsquedas por curso y por profesor.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    /** Servicio que contiene la lógica de negocio de asignaturas. */
    private final AsignaturaService asignaturaService;

    /**
     * Obtiene la lista de asignaturas asociadas a un curso.
     *
     * @param cursoId identificador del curso
     * @return lista de DTOs con la información de las asignaturas del curso
     */
    @GetMapping("/curso/{cursoId}")
    public List<AsignaturaDTO> getByCurso(@PathVariable Integer cursoId) {
        return asignaturaService.getByCurso(cursoId);
    }

    /**
     * Obtiene la lista de asignaturas impartidas por un profesor.
     *
     * @param profesorId identificador del profesor
     * @return lista de DTOs con la información de las asignaturas del profesor
     */
    @GetMapping("/profesor/{profesorId}")
    public List<AsignaturaDTO> getByProfesor(@PathVariable Integer profesorId) {
        return asignaturaService.getByProfesor(profesorId);
    }

    /**
     * Crea una nueva asignatura en el sistema.
     *
     * @param dto datos de la asignatura a crear
     * @return DTO con la información de la asignatura creada
     */
    @PostMapping("/crear")
    public AsignaturaDTO createAsignatura(@RequestBody AsignaturaDTO dto) {
        return asignaturaService.createAsignatura(dto);
    }

    /**
     * Actualiza los datos de una asignatura existente.
     *
     * @param id  identificador de la asignatura a actualizar
     * @param dto datos actualizados de la asignatura
     * @return DTO con la información de la asignatura actualizada
     */
    @PutMapping("/editar/{id}")
    public AsignaturaDTO updateAsignatura(@PathVariable Integer id, @RequestBody AsignaturaDTO dto) {
        return asignaturaService.updateAsignatura(id, dto);
    }
}
