package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.NotificacionDTO;
import com.spring.learnsphere.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping("/usuario/{userId}")
    public List<NotificacionDTO> getByUsuario(@PathVariable Integer userId) {
        return notificacionService.getByUsuario(userId);
    }

    @PutMapping("/leer/{id}")
    public void marcarLeida(@PathVariable Integer id) {
        notificacionService.marcarLeida(id);
    }

}
