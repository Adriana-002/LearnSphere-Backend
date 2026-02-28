package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @GetMapping("/buscar/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.findUsuarioById(id);
    }

    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("/editar/{id}")
    public Usuario editarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
    }

}
