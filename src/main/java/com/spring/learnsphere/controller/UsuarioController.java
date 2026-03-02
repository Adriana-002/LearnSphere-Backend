package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.UsuarioDTO;
import com.spring.learnsphere.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los usuarios en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta y actualización de datos de usuarios,
 * incluyendo la obtención del usuario autenticado.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    /** Servicio que contiene la lógica de negocio de usuarios. */
    private final UsuarioService usuarioService;

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id identificador del usuario
     * @return DTO con la información del usuario encontrado
     */
    @GetMapping("/buscar/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id  identificador del usuario a actualizar
     * @param dto datos actualizados del usuario
     * @return DTO con la información del usuario actualizado
     */
    @PutMapping("/editar/{id}")
    public UsuarioDTO editar(@PathVariable Integer id, @RequestBody UsuarioDTO dto) {
        return usuarioService.update(id, dto);
    }

    /**
     * Obtiene la información del usuario autenticado a partir de la cabecera X-User-Id.
     *
     * @param userId identificador del usuario proporcionado en la cabecera de la petición
     * @return respuesta con el DTO del usuario, o error 401/404 si no se proporciona o no existe
     */
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
