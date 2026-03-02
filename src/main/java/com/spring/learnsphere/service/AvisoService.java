package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.AvisoDTO;
import com.spring.learnsphere.model.Aviso;
import com.spring.learnsphere.repository.AvisoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los avisos en el sistema LearnSphere.
 *
 * Proporciona operaciones para la creación, consulta y eliminación de avisos,
 * incluyendo la gestión de avisos marcados como importantes.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class AvisoService {

    /** Repositorio para acceder a los datos de avisos. */
    private final AvisoRepository avisoRepository;

    /**
     * Obtiene la lista de todos los avisos ordenados por fecha de publicación descendente.
     *
     * @return lista de DTOs con la información de todos los avisos
     */
    public List<AvisoDTO> getAllAvisos() {
        return avisoRepository.findAllByOrderByFechaPublicacionDesc()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Obtiene la lista de avisos marcados como importantes.
     *
     * @return lista de DTOs con la información de los avisos importantes
     */
    public List<AvisoDTO> getImportantes() {
        return avisoRepository.findByEsImportanteTrue()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Crea un nuevo aviso en el sistema.
     *
     * @param dto datos del aviso a crear
     * @return DTO con la información del aviso creado
     */
    public AvisoDTO createAviso(AvisoDTO dto) {
        Aviso aviso = new Aviso();
        aviso.setTitulo(dto.getTitulo());
        aviso.setMensaje(dto.getMensaje());
        aviso.setEsImportante(dto.getEsImportante());
        return toDTO(avisoRepository.save(aviso));
    }

    /**
     * Elimina un aviso del sistema.
     *
     * @param id identificador del aviso a eliminar
     */
    public void deleteAviso(Integer id) {
        avisoRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Aviso a su correspondiente DTO.
     *
     * @param a entidad Aviso a convertir
     * @return DTO con la información del aviso
     */
    private AvisoDTO toDTO(Aviso a) {
        AvisoDTO dto = new AvisoDTO();
        dto.setAvisoId(a.getId());
        dto.setTitulo(a.getTitulo());
        dto.setMensaje(a.getMensaje());
        dto.setEsImportante(a.getEsImportante());
        dto.setFechaPublicacion(a.getFechaPublicacion() != null ? a.getFechaPublicacion().toString() : null);
        return dto;
    }

}
