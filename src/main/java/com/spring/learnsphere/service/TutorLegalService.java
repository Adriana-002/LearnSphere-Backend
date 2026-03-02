package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.TutorLegalDTO;
import com.spring.learnsphere.model.TutorLegal;
import com.spring.learnsphere.repository.TutorLegalRepository;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los tutores legales en el sistema LearnSphere.
 *
 * Proporciona operaciones para la consulta y actualización de datos de tutores legales,
 * incluyendo la información de usuario asociada.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class TutorLegalService {

    /** Repositorio para acceder a los datos de tutores legales. */
    private final TutorLegalRepository tutorLegalRepository;

    /** Repositorio para acceder a los datos de usuarios. */
    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene la lista de todos los tutores legales del sistema.
     *
     * @return lista de DTOs con la información de todos los tutores legales
     */
    public List<TutorLegalDTO> getAll() {
        return tutorLegalRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Busca un tutor legal por su identificador único.
     *
     * @param id identificador del tutor legal
     * @return DTO con la información del tutor legal encontrado
     * @throws RuntimeException si el tutor legal no existe
     */
    public TutorLegalDTO findById(Integer id) {
        return toDTO(tutorLegalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado")));
    }

    /**
     * Actualiza los datos de un tutor legal existente.
     *
     * @param id  identificador del tutor legal a actualizar
     * @param dto datos actualizados del tutor legal
     * @return DTO con la información del tutor legal actualizado
     * @throws RuntimeException si el tutor legal no existe
     */
    public TutorLegalDTO update(Integer id, TutorLegalDTO dto) {
        TutorLegal tutor = tutorLegalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));
        tutor.setEsAlumno(dto.getEsAlumno());
        return toDTO(tutorLegalRepository.save(tutor));
    }

    /**
     * Convierte una entidad TutorLegal a su correspondiente DTO.
     *
     * @param t entidad TutorLegal a convertir
     * @return DTO con la información del tutor legal
     */
    private TutorLegalDTO toDTO(TutorLegal t) {
        TutorLegalDTO dto = new TutorLegalDTO();
        dto.setUserId(t.getId());
        dto.setEsAlumno(t.getEsAlumno());
        usuarioRepository.findById(t.getId()).ifPresent(u -> {
            dto.setNombre(u.getNombre());
            dto.setApellidos(u.getApellidos());
            dto.setEmail(u.getEmail());
            dto.setTelefono(u.getTelefono());
        });
        return dto;
    }

}
