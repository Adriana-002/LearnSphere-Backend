package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AuthLoginRequest;
import com.spring.learnsphere.dto.AuthLoginResponse;
import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que gestiona las peticiones HTTP de autenticación en el sistema LearnSphere.
 *
 * Expone endpoints para el inicio de sesión de usuarios y la generación
 * de hashes de contraseñas mediante BCrypt.
 *
 * @author Adriana
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /** Repositorio para acceder a los datos de usuarios. */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /** Codificador BCrypt para la verificación de contraseñas. */
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Autentica a un usuario mediante correo electrónico y contraseña.
     *
     * @param request objeto con las credenciales de inicio de sesión
     * @return respuesta con el token y datos del usuario si las credenciales son válidas,
     *         o un error 401 si son incorrectas
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail());

        if (usuario == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        if (!encoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        AuthLoginResponse response = new AuthLoginResponse(
                "token-" + usuario.getId(),
                usuario.getId(),
                usuario.getNombre(),
                usuario.getRol()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Genera un hash BCrypt a partir de una contraseña en texto plano.
     *
     * @param password contraseña en texto plano a codificar
     * @return hash BCrypt de la contraseña proporcionada
     */
    @GetMapping("/hash")
    public String generarHash(@RequestParam String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
