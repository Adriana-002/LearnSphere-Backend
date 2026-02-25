package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AuthLoginRequest;
import com.spring.learnsphere.dto.AuthLoginResponse;
import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest request) {

        // 1. Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail());

        if (usuario == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        // 2. Comparar contraseña con el hash guardado en BD
        if (!encoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        // 3. Devolver los datos que necesita Android
        AuthLoginResponse response = new AuthLoginResponse(
                "token-" + usuario.getId(),  // token temporal, luego será JWT
                usuario.getId(),
                usuario.getNombre(),
                usuario.getRol()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/hash")
    public String generarHash(@RequestParam String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
