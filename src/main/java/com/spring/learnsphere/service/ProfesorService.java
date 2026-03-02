package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.ProfesorDTO;
import com.spring.learnsphere.model.Profesor;
import com.spring.learnsphere.repository.ProfesorRepository;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los profesores en el sistema LearnSphere.
 *
 * Proporciona operaciones para la consulta y actualización de datos de profesores.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class ProfesorService {

    /** Repositorio para acceder a los datos de profesores. */
    private final ProfesorRepository profesorRepository;

    /** Repositorio para acceder a los datos de usuarios. */
    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene la lista de todos los profesores del sistema.
     *
     * @return lista de DTOs con la información de todos los profesores
     */
    public List<ProfesorDTO> getAll() {
        return profesorRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Busca un profesor por su identificador único.
     *
     * @param id identificador del profesor
     * @return DTO con la información del profesor encontrado
     * @throws RuntimeException si el profesor no existe
     */
    public ProfesorDTO findById(Integer id) {
        return toDTO(profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado")));
    }

    /**
     * Actualiza los datos de un profesor existente.
     *
     * @param id  identificador del profesor a actualizar
     * @param dto datos actualizados del profesor
     * @return DTO con la información del profesor actualizado
     * @throws RuntimeException si el profesor no existe
     */
    public ProfesorDTO update(Integer id, ProfesorDTO dto) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        profesor.setDepartamento(dto.getDepartamento());
        return toDTO(profesorRepository.save(profesor));
    }

    /**
     * Convierte una entidad Profesor a su correspondiente DTO.
     *
     * @param p entidad Profesor a convertir
     * @return DTO con la información del profesor
     */
    private ProfesorDTO toDTO(Profesor p) {
        ProfesorDTO dto = new ProfesorDTO();
        dto.setUserId(p.getId());
        dto.setDepartamento(p.getDepartamento());
        usuarioRepository.findById(p.getId()).ifPresent(u -> {
            dto.setNombre(u.getNombre());
            dto.setApellidos(u.getApellidos());
            dto.setEmail(u.getEmail());
        });
        return dto;
    }

}
