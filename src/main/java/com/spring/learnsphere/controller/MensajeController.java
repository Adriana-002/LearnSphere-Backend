package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Mensaje;
import com.spring.learnsphere.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    @GetMapping("/listar")
    public List<Mensaje> listarMensajes() {
        return mensajeService.findAllMensajes();
    }

    @GetMapping("/buscar/{id}")
    public Mensaje buscarMensajePorId(@PathVariable Integer id) {
        return mensajeService.findMensajeById(id);
    }

    @PostMapping("/crear")
    public Mensaje crearMensaje(@RequestBody Mensaje mensaje) {
        return mensajeService.createMensaje(mensaje);
    }

    @PutMapping("/editar/{id}")
    public Mensaje editarMensaje(@PathVariable Integer id, @RequestBody Mensaje mensaje) {
        return mensajeService.updateMensaje(id, mensaje);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMensaje(@PathVariable Integer id) {
        mensajeService.deleteMensaje(id);
    }

}
