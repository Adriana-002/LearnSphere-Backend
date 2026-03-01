package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.MensajeDTO;
import com.spring.learnsphere.model.Chat;
import com.spring.learnsphere.model.Mensaje;
import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.repository.ChatRepository;
import com.spring.learnsphere.repository.MensajeRepository;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final ChatRepository chatRepository;
    private final UsuarioRepository usuarioRepository;

    public List<MensajeDTO> getByChatId(Integer chatId) {
        return mensajeRepository.findByChat_Id(chatId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MensajeDTO createMensaje(MensajeDTO dto) {
        Chat chat = chatRepository.findById(dto.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        Usuario user = usuarioRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setUser(user);
        mensaje.setTexto(dto.getTexto());

        return toDTO(mensajeRepository.save(mensaje));
    }

    public void deleteMensaje(Integer id) {
        mensajeRepository.deleteById(id);
    }

    private MensajeDTO toDTO(Mensaje m) {
        MensajeDTO dto = new MensajeDTO();
        dto.setMensajeId(m.getId());
        dto.setChatId(m.getChat() != null ? m.getChat().getId() : null);
        dto.setUserId(m.getUser() != null ? m.getUser().getId() : null);
        dto.setTexto(m.getTexto());
        dto.setFechaEnvio(m.getFechaEnvio() != null ? m.getFechaEnvio().toString() : null);
        dto.setNombreRemitente(m.getUser() != null ? m.getUser().getNombre() : null);
        return dto;
    }

}
