package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Chat;
import com.spring.learnsphere.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public List<Chat> findAllChats() {
        return chatRepository.findAll();
    }

    public Chat findChatById(Integer id) {
        return chatRepository.findById(id).orElseThrow(() -> new RuntimeException("Chat no encontrado"));
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public Chat updateChat(Integer id, Chat chatDetails) {
        Chat chat = findChatById(id);

        if (chat == null) {
            throw new IllegalArgumentException("Chat no encontrado");
        }
        chat.setTipo(chatDetails.getTipo());
        chat.setFechaCreacion(chatDetails.getFechaCreacion());
        return createChat(chat);
    }

    public void deleteChat(Integer id) {
        if (findChatById(id) == null) {
            throw new IllegalArgumentException("Chat no encontrado");
        }
        chatRepository.deleteById(id);
    }

}
