package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.AlumnoDTO;
import com.spring.learnsphere.model.*;
import com.spring.learnsphere.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los alumnos en el sistema LearnSphere.
 *
 * Proporciona operaciones CRUD y consultas específicas para la gestión de alumnos,
 * incluyendo búsquedas por tutor, profesor y curso.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class AlumnoService {

    /** Repositorio para acceder a los datos de alumnos. */
    private final AlumnoRepository alumnoRepository;

    /** Repositorio para acceder a las relaciones tutor-alumno. */
    private final TutorAlumnoRepository tutorAlumnoRepository;

    /** Repositorio para acceder a las relaciones profesor-asignatura. */
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;

    /** Repositorio para acceder a las relaciones curso-asignatura. */
    private final CursoAsignaturaRepository cursoAsignaturaRepository;

    /** Repositorio para acceder a las relaciones alumno-curso. */
    private final AlumnoCursoRepository alumnoCursoRepository;

    /**
     * Obtiene la lista de todos los alumnos del sistema.
     *
     * @return lista de DTOs con la información de todos los alumnos
     */
    public List<AlumnoDTO> getAllAlumnos() {
        return alumnoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Busca un alumno por su identificador único.
     *
     * @param id identificador del alumno
     * @return DTO con la información del alumno encontrado
     * @throws RuntimeException si el alumno no existe
     */
    public AlumnoDTO findAlumnoById(Integer id) {
        return toDTO(alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado")));
    }

    /**
     * Obtiene el alumno asociado a un tutor legal.
     *
     * @param tutorId identificador del tutor legal
     * @return DTO con la información del alumno, o null si no se encuentra
     */
    public AlumnoDTO getByTutor(Integer tutorId) {
        return tutorAlumnoRepository.findAllByTutor_Id(tutorId)
                .stream()
                .map(ta -> toDTO(ta.getAlumno()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtiene la lista de alumnos asociados a un profesor a través de sus asignaturas y cursos.
     *
     * @param profesorId identificador del profesor
     * @return lista de DTOs con la información de los alumnos del profesor
     */
    public List<AlumnoDTO> getByProfesor(Integer profesorId) {
        List<Integer> asignaturaIds = profesorAsignaturaRepository.findByProfesor_Id(profesorId)
                .stream()
                .map(pa -> pa.getAsignatura().getId())
                .collect(Collectors.toList());

        List<Integer> alumnoIds = cursoAsignaturaRepository.findAll().stream()
                .filter(ca -> asignaturaIds.contains(ca.getAsignatura().getId()))
                .flatMap(ca -> alumnoCursoRepository.findAll().stream()
                        .filter(ac -> ac.getCurso().getId().equals(ca.getCurso().getId())))
                .map(ac -> ac.getAlumno().getId())
                .distinct()
                .collect(Collectors.toList());

        return alumnoRepository.findAllById(alumnoIds)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Obtiene la lista de alumnos de un curso específico.
     *
     * @param cursoId identificador del curso
     * @return lista de DTOs con la información de los alumnos del curso
     */
    public List<AlumnoDTO> getByCurso(Integer cursoId) {
        return alumnoCursoRepository.findByCursoId(cursoId).stream()
                .map(ac -> toDTO(ac.getAlumno()))
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuevo alumno en el sistema.
     *
     * @param dto datos del alumno a crear
     * @return DTO con la información del alumno creado
     */
    public AlumnoDTO createAlumno(AlumnoDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        return toDTO(alumnoRepository.save(alumno));
    }

    /**
     * Actualiza los datos de un alumno existente.
     *
     * @param id  identificador del alumno a actualizar
     * @param dto datos actualizados del alumno
     * @return DTO con la información del alumno actualizado
     * @throws RuntimeException si el alumno no existe
     */
    public AlumnoDTO updateAlumno(Integer id, AlumnoDTO dto) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        return toDTO(alumnoRepository.save(alumno));
    }

    /**
     * Elimina un alumno del sistema.
     *
     * @param id identificador del alumno a eliminar
     */
    public void deleteAlumno(Integer id) {
        alumnoRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Alumno a su correspondiente DTO.
     *
     * @param a entidad Alumno a convertir
     * @return DTO con la información del alumno
     */
    private AlumnoDTO toDTO(Alumno a) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.setAlumnoId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setApellidos(a.getApellidos());
        dto.setFechaNacimiento(a.getFechaNacimiento() != null ? a.getFechaNacimiento().toString() : null);
        dto.setFotoUrl(a.getFotoUrl());
        alumnoCursoRepository.findAll().stream()
                .filter(ac -> ac.getAlumno().getId().equals(a.getId()))
                .findFirst()
                .ifPresent(ac -> dto.setCursoId(ac.getCurso().getId()));
        return dto;
    }

}
