package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.AsignaturaDTO;
import com.spring.learnsphere.model.Asignatura;
import com.spring.learnsphere.repository.AsignaturaRepository;
import com.spring.learnsphere.repository.CursoAsignaturaRepository;
import com.spring.learnsphere.repository.ProfesorAsignaturaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con las asignaturas en el sistema LearnSphere.
 *
 * Proporciona operaciones CRUD y consultas específicas para la gestión de asignaturas,
 * incluyendo búsquedas por curso y por profesor.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class AsignaturaService {

    /** Repositorio para acceder a los datos de asignaturas. */
    private final AsignaturaRepository asignaturaRepository;

    /** Repositorio para acceder a las relaciones curso-asignatura. */
    private final CursoAsignaturaRepository cursoAsignaturaRepository;

    /** Repositorio para acceder a las relaciones profesor-asignatura. */
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;

    /**
     * Obtiene la lista de asignaturas asociadas a un curso.
     *
     * @param cursoId identificador del curso
     * @return lista de DTOs con la información de las asignaturas del curso
     */
    public List<AsignaturaDTO> getByCurso(Integer cursoId) {
        return cursoAsignaturaRepository.findAll().stream()
                .filter(ca -> ca.getCurso().getId().equals(cursoId))
                .map(ca -> toDTO(ca.getAsignatura()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene la lista de asignaturas impartidas por un profesor.
     *
     * @param profesorId identificador del profesor
     * @return lista de DTOs con la información de las asignaturas del profesor
     */
    public List<AsignaturaDTO> getByProfesor(Integer profesorId) {
        return profesorAsignaturaRepository.findByProfesor_Id(profesorId).stream()
                .map(pa -> toDTO(pa.getAsignatura()))
                .collect(Collectors.toList());
    }

    /**
     * Crea una nueva asignatura en el sistema.
     *
     * @param dto datos de la asignatura a crear
     * @return DTO con la información de la asignatura creada
     */
    public AsignaturaDTO createAsignatura(AsignaturaDTO dto) {
        Asignatura a = new Asignatura();
        a.setNombre(dto.getNombre());
        return toDTO(asignaturaRepository.save(a));
    }

    /**
     * Actualiza los datos de una asignatura existente.
     *
     * @param id  identificador de la asignatura a actualizar
     * @param dto datos actualizados de la asignatura
     * @return DTO con la información de la asignatura actualizada
     * @throws RuntimeException si la asignatura no existe
     */
    public AsignaturaDTO updateAsignatura(Integer id, AsignaturaDTO dto) {
        Asignatura a = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        a.setNombre(dto.getNombre());
        return toDTO(asignaturaRepository.save(a));
    }

    /**
     * Convierte una entidad Asignatura a su correspondiente DTO.
     *
     * @param a entidad Asignatura a convertir
     * @return DTO con la información de la asignatura
     */
    private AsignaturaDTO toDTO(Asignatura a) {
        AsignaturaDTO dto = new AsignaturaDTO();
        dto.setAsignaturaId(a.getId());
        dto.setNombre(a.getNombre());
        return dto;
    }

}
