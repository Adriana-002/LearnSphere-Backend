package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Integer id, Usuario usuarioDetails) {
        Usuario usuario = findUsuarioById(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setPasswordHash(usuarioDetails.getPasswordHash());
        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellidos(usuarioDetails.getApellidos());
        usuario.setTelefono(usuarioDetails.getTelefono());
        usuario.setRol(usuarioDetails.getRol());
        usuario.setFechaRegistro(usuarioDetails.getFechaRegistro());
        return createUsuario(usuario);
    }

    public void deleteUsuario(Integer id) {
        if (findUsuarioById(id) == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

}
