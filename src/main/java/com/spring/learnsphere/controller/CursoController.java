package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.CursoDTO;
import com.spring.learnsphere.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los cursos en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación, actualización y eliminación de cursos,
 * incluyendo la búsqueda del curso asignado a un alumno.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cursos")
public class CursoController {

    /** Servicio que contiene la lógica de negocio de cursos. */
    private final CursoService cursoService;

    /**
     * Obtiene la lista de todos los cursos del sistema.
     *
     * @return lista de DTOs con la información de todos los cursos
     */
    @GetMapping("/listar")
    public List<CursoDTO> listar() {
        return cursoService.getAll();
    }

    /**
     * Busca un curso por su identificador único.
     *
     * @param id identificador del curso
     * @return DTO con la información del curso encontrado
     */
    @GetMapping("/buscar/{id}")
    public CursoDTO buscarPorId(@PathVariable Integer id) {
        return cursoService.findById(id);
    }

    /**
     * Obtiene el curso al que pertenece un alumno.
     *
     * @param alumnoId identificador del alumno
     * @return DTO con la información del curso del alumno
     */
    @GetMapping("/alumno/{alumnoId}")
    public CursoDTO getByAlumno(@PathVariable Integer alumnoId) {
        return cursoService.getByAlumno(alumnoId);
    }

    /**
     * Crea un nuevo curso en el sistema.
     *
     * @param dto datos del curso a crear
     * @return DTO con la información del curso creado
     */
    @PostMapping("/crear")
    public CursoDTO crear(@RequestBody CursoDTO dto) {
        return cursoService.create(dto);
    }

    /**
     * Actualiza los datos de un curso existente.
     *
     * @param id  identificador del curso a actualizar
     * @param dto datos actualizados del curso
     * @return DTO con la información del curso actualizado
     */
    @PutMapping("/editar/{id}")
    public CursoDTO editar(@PathVariable Integer id, @RequestBody CursoDTO dto) {
        return cursoService.update(id, dto);
    }

    /**
     * Elimina un curso del sistema.
     *
     * @param id identificador del curso a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        cursoService.delete(id);
    }

}
