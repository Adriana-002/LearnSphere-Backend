package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Chat;
import com.spring.learnsphere.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/listar")
    public List<Chat> listarChats() {
        return chatService.findAllChats();
    }

    @GetMapping("/buscar/{id}")
    public Chat buscarChatPorId(@PathVariable Integer id) {
        return chatService.findChatById(id);
    }

    @PostMapping("/crear")
    public Chat crearChat(@RequestBody Chat chat) {
        return chatService.createChat(chat);
    }

    @PutMapping("/editar/{id}")
    public Chat editarChat(@PathVariable Integer id, @RequestBody Chat chat) {
        return chatService.updateChat(id, chat);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarChat(@PathVariable Integer id) {
        chatService.deleteChat(id);
    }

}
