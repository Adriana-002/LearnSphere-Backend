package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AlumnoDTO;
import com.spring.learnsphere.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los alumnos en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación, actualización y eliminación de alumnos,
 * así como búsquedas por tutor, profesor y curso.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alumnos")
public class AlumnoController {

    /** Servicio que contiene la lógica de negocio de alumnos. */
    private final AlumnoService alumnoService;

    /**
     * Obtiene la lista de todos los alumnos del sistema.
     *
     * @return lista de DTOs con la información de todos los alumnos
     */
    @GetMapping("/listar")
    public List<AlumnoDTO> listarAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    /**
     * Busca un alumno por su identificador único.
     *
     * @param id identificador del alumno
     * @return DTO con la información del alumno encontrado
     */
    @GetMapping("/buscar/{id}")
    public AlumnoDTO buscarAlumnoPorId(@PathVariable Integer id) {
        return alumnoService.findAlumnoById(id);
    }

    /**
     * Obtiene el alumno asociado a un tutor legal.
     *
     * @param tutorId identificador del tutor legal
     * @return DTO con la información del alumno del tutor
     */
    @GetMapping("/tutor/{tutorId}")
    public AlumnoDTO getByTutor(@PathVariable Integer tutorId) {
        return alumnoService.getByTutor(tutorId);
    }

    /**
     * Obtiene la lista de alumnos asociados a un profesor.
     *
     * @param profesorId identificador del profesor
     * @return lista de DTOs con la información de los alumnos del profesor
     */
    @GetMapping("/profesor/{profesorId}")
    public List<AlumnoDTO> getByProfesor(@PathVariable Integer profesorId) {
        return alumnoService.getByProfesor(profesorId);
    }

    /**
     * Obtiene la lista de alumnos de un curso específico.
     *
     * @param cursoId identificador del curso
     * @return lista de DTOs con la información de los alumnos del curso
     */
    @GetMapping("/curso/{cursoId}")
    public List<AlumnoDTO> getByCurso(@PathVariable Integer cursoId) {
        return alumnoService.getByCurso(cursoId);
    }

    /**
     * Crea un nuevo alumno en el sistema.
     *
     * @param dto datos del alumno a crear
     * @return DTO con la información del alumno creado
     */
    @PostMapping("/crear")
    public AlumnoDTO crearAlumno(@RequestBody AlumnoDTO dto) {
        return alumnoService.createAlumno(dto);
    }

    /**
     * Actualiza los datos de un alumno existente.
     *
     * @param id  identificador del alumno a actualizar
     * @param dto datos actualizados del alumno
     * @return DTO con la información del alumno actualizado
     */
    @PutMapping("/editar/{id}")
    public AlumnoDTO editarAlumno(@PathVariable Integer id, @RequestBody AlumnoDTO dto) {
        return alumnoService.updateAlumno(id, dto);
    }

    /**
     * Elimina un alumno del sistema.
     *
     * @param id identificador del alumno a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public void eliminarAlumno(@PathVariable Integer id) {
        alumnoService.deleteAlumno(id);
    }

}
