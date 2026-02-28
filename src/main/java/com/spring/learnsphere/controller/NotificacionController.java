package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping("/listar")
    public List<Notificacion> listarNotificaciones() {
        return notificacionService.findAllNotificaciones();
    }

    @GetMapping("/buscar/{id}")
    public Notificacion buscarNotificacionPorId(@PathVariable Integer id) {
        return notificacionService.findNotificacionById(id);
    }

    @PostMapping("/crear")
    public Notificacion crearNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.createNotificacion(notificacion);
    }

    @PutMapping("/editar/{id}")
    public Notificacion editarNotificacion(@PathVariable Integer id, @RequestBody Notificacion notificacion) {
        return notificacionService.updateNotificacion(id, notificacion);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarNotificacion(@PathVariable Integer id) {
        notificacionService.deleteNotificacion(id);
    }

}
