package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.CursoDTO;
import com.spring.learnsphere.model.Curso;
import com.spring.learnsphere.repository.AlumnoCursoRepository;
import com.spring.learnsphere.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los cursos en el sistema LearnSphere.
 *
 * Proporciona operaciones CRUD y consultas específicas para la gestión de cursos,
 * incluyendo la búsqueda del curso asignado a un alumno.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class CursoService {

    /** Repositorio para acceder a los datos de cursos. */
    private final CursoRepository cursoRepository;

    /** Repositorio para acceder a las relaciones alumno-curso. */
    private final AlumnoCursoRepository alumnoCursoRepository;

    /**
     * Obtiene la lista de todos los cursos del sistema.
     *
     * @return lista de DTOs con la información de todos los cursos
     */
    public List<CursoDTO> getAll() {
        return cursoRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Busca un curso por su identificador único.
     *
     * @param id identificador del curso
     * @return DTO con la información del curso encontrado
     * @throws RuntimeException si el curso no existe
     */
    public CursoDTO findById(Integer id) {
        return toDTO(cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));
    }

    /**
     * Obtiene el curso al que pertenece un alumno.
     *
     * @param alumnoId identificador del alumno
     * @return DTO con la información del curso del alumno
     * @throws RuntimeException si no se encuentra un curso para el alumno
     */
    public CursoDTO getByAlumno(Integer alumnoId) {
        return alumnoCursoRepository.findAll().stream()
                .filter(ac -> ac.getAlumno().getId().equals(alumnoId))
                .findFirst()
                .map(ac -> toDTO(ac.getCurso()))
                .orElseThrow(() -> new RuntimeException("Curso no encontrado para este alumno"));
    }

    /**
     * Crea un nuevo curso en el sistema.
     *
     * @param dto datos del curso a crear
     * @return DTO con la información del curso creado
     */
    public CursoDTO create(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());
        curso.setAñoAcademico(dto.getAñoAcademico());
        return toDTO(cursoRepository.save(curso));
    }

    /**
     * Actualiza los datos de un curso existente.
     *
     * @param id  identificador del curso a actualizar
     * @param dto datos actualizados del curso
     * @return DTO con la información del curso actualizado
     * @throws RuntimeException si el curso no existe
     */
    public CursoDTO update(Integer id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setNombre(dto.getNombre());
        curso.setAñoAcademico(dto.getAñoAcademico());
        return toDTO(cursoRepository.save(curso));
    }

    /**
     * Elimina un curso del sistema.
     *
     * @param id identificador del curso a eliminar
     */
    public void delete(Integer id) {
        cursoRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Curso a su correspondiente DTO.
     *
     * @param c entidad Curso a convertir
     * @return DTO con la información del curso
     */
    private CursoDTO toDTO(Curso c) {
        CursoDTO dto = new CursoDTO();
        dto.setCursoId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setAñoAcademico(c.getAñoAcademico());
        return dto;
    }

}
