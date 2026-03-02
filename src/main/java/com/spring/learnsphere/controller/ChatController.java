package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.ChatDTO;
import com.spring.learnsphere.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los chats en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta y creación de chats entre usuarios.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    /** Servicio que contiene la lógica de negocio de chats. */
    private final ChatService chatService;

    /**
     * Obtiene la lista de chats en los que participa un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de DTOs con la información de los chats del usuario
     */
    @GetMapping("/usuario/{userId}")
    public List<ChatDTO> getByUsuario(@PathVariable Integer userId) {
        return chatService.getByUsuario(userId);
    }

    /**
     * Crea un nuevo chat y añade a los participantes correspondientes.
     *
     * @param dto datos del chat a crear, incluyendo el creador y los alumnos
     * @return DTO con la información del chat creado
     */
    @PostMapping("/crear")
    public ChatDTO createChat(@RequestBody ChatDTO dto) {
        return chatService.createChat(dto);
    }

}
