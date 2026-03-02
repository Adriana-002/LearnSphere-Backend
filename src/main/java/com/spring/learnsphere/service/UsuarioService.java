package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.UsuarioDTO;
import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los usuarios en el sistema LearnSphere.
 *
 * Proporciona operaciones para la consulta y actualización de datos de usuarios,
 * incluyendo la búsqueda por identificador y por correo electrónico.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class UsuarioService {

    /** Repositorio para acceder a los datos de usuarios. */
    private final UsuarioRepository usuarioRepository;

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id identificador del usuario
     * @return DTO con la información del usuario encontrado
     * @throws RuntimeException si el usuario no existe
     */
    public UsuarioDTO findById(Integer id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDTO(u);
    }

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email correo electrónico del usuario
     * @return entidad Usuario encontrada, o null si no existe
     */
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id  identificador del usuario a actualizar
     * @param dto datos actualizados del usuario
     * @return DTO con la información del usuario actualizado
     * @throws RuntimeException si el usuario no existe
     */
    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        u.setNombre(dto.getNombre());
        u.setApellidos(dto.getApellidos());
        u.setTelefono(dto.getTelefono());
        return toDTO(usuarioRepository.save(u));
    }

    /**
     * Convierte una entidad Usuario a su correspondiente DTO.
     *
     * @param u entidad Usuario a convertir
     * @return DTO con la información del usuario
     */
    private UsuarioDTO toDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUserId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setNombre(u.getNombre());
        dto.setApellidos(u.getApellidos());
        dto.setTelefono(u.getTelefono());
        dto.setRol(u.getRol());
        dto.setFechaRegistro(u.getFechaRegistro() != null ? u.getFechaRegistro().toString() : null);
        return dto;
    }

}
