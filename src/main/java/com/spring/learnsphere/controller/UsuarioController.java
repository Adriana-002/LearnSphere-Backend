package com.spring.learnsphere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Integer id) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerPorEmail(@PathVariable String email) {
        return usuarioService.obtenerUsuarioPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuario>> listarPorRol(@PathVariable String rol) {
        return ResponseEntity.ok(usuarioService.listarPorRol(rol));
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.existeEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        Usuario nuevo = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevo);
    }

    //HASTA LOS COJONES DE ESTE METODO AAAAAAAAAAAAAAAAAAAAAH
    //VOLVER A MIRAR
    //error solucionado
    //vuelve un error
    //Metodo entero cambiado
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> optional = usuarioService.obtenerUsuarioPorId(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Usuario existente = optional.get();

        if (usuario.getEmail() != null && !usuario.getEmail().equals(existente.getEmail())) {
            if (usuarioService.existeEmail(usuario.getEmail())) {
                return ResponseEntity.badRequest().build();
            }
            existente.setEmail(usuario.getEmail());
        }

        existente.setNombre(usuario.getNombre());
        existente.setApellidos(usuario.getApellidos());
        existente.setTelefono(usuario.getTelefono());
        existente.setRol(usuario.getRol());

        if (usuario.getPasswordHash() != null) {
            existente.setPasswordHash(usuario.getPasswordHash());
        }

        usuarioService.guardarUsuario(existente);
        return ResponseEntity.ok(existente);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
