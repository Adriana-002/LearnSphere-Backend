package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.UsuarioDTO;
import com.spring.learnsphere.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/buscar/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/editar/{id}")
    public UsuarioDTO editar(@PathVariable Integer id, @RequestBody UsuarioDTO dto) {
        return usuarioService.update(id, dto);
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> me(@RequestHeader(value = "X-User-Id", required = false) Integer userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioDTO dto = usuarioService.findById(userId);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dto);
    }

}
