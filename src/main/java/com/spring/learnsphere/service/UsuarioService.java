package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.UsuarioDTO;
import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO findById(Integer id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDTO(u);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        u.setNombre(dto.getNombre());
        u.setApellidos(dto.getApellidos());
        u.setTelefono(dto.getTelefono());
        return toDTO(usuarioRepository.save(u));
    }

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
