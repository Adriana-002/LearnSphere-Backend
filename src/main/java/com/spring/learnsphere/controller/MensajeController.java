package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.MensajeDTO;
import com.spring.learnsphere.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los mensajes en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación y eliminación de mensajes dentro de chats.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mensajes")
public class MensajeController {

    /** Servicio que contiene la lógica de negocio de mensajes. */
    private final MensajeService mensajeService;

    /**
     * Obtiene la lista de mensajes de un chat.
     *
     * @param chatId identificador del chat
     * @return lista de DTOs con la información de los mensajes del chat
     */
    @GetMapping("/chat/{chatId}")
    public List<MensajeDTO> getByChatId(@PathVariable Integer chatId) {
        return mensajeService.getByChatId(chatId);
    }

    /**
     * Crea un nuevo mensaje dentro de un chat.
     *
     * @param dto datos del mensaje a crear
     * @return DTO con la información del mensaje creado
     */
    @PostMapping("/crear")
    public MensajeDTO createMensaje(@RequestBody MensajeDTO dto) {
        return mensajeService.createMensaje(dto);
    }

    /**
     * Elimina un mensaje del sistema.
     *
     * @param id identificador del mensaje a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public void deleteMensaje(@PathVariable Integer id) {
        mensajeService.deleteMensaje(id);
    }

}
