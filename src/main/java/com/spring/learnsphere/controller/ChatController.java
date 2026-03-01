package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.ChatDTO;
import com.spring.learnsphere.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/usuario/{userId}")
    public List<ChatDTO> getByUsuario(@PathVariable Integer userId) {
        return chatService.getByUsuario(userId);
    }

    @PostMapping("/crear")
    public ChatDTO createChat(@RequestBody ChatDTO dto) {
        return chatService.createChat(dto);
    }

}
