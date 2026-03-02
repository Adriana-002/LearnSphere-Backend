package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.NotificacionDTO;
import com.spring.learnsphere.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con las notificaciones en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta de notificaciones por usuario,
 * el filtrado de notificaciones importantes y el marcado como leídas.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    /** Servicio que contiene la lógica de negocio de notificaciones. */
    private final NotificacionService notificacionService;

    /**
     * Obtiene la lista de notificaciones de un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de DTOs con la información de las notificaciones del usuario
     */
    @GetMapping("/usuario/{userId}")
    public List<NotificacionDTO> getByUsuario(@PathVariable Integer userId) {
        return notificacionService.getByUsuario(userId);
    }

    /**
     * Obtiene las notificaciones importantes de un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de DTOs con las notificaciones importantes del usuario
     */
    @GetMapping("/usuario/{userId}/importantes")
    public List<NotificacionDTO> getImportantesByUsuario(@PathVariable Integer userId) {
        return notificacionService.getImportantesByUsuario(userId);
    }

    /**
     * Marca una notificación como leída.
     *
     * @param id identificador de la notificación a marcar
     */
    @PutMapping("/leer/{id}")
    public void marcarLeida(@PathVariable Integer id) {
        notificacionService.marcarLeida(id);
    }

}
