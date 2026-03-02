package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.MensajeDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.Chat;
import com.spring.learnsphere.model.Mensaje;
import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.model.Usuario;
import com.spring.learnsphere.model.UsuarioChat;
import com.spring.learnsphere.repository.ChatRepository;
import com.spring.learnsphere.repository.MensajeRepository;
import com.spring.learnsphere.repository.NotificacionRepository;
import com.spring.learnsphere.repository.UsuarioChatRepository;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final ChatRepository chatRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacionRepository notificacionRepository;
    private final UsuarioChatRepository usuarioChatRepository;

    public List<MensajeDTO> getByChatId(Integer chatId) {
        return mensajeRepository.findByChat_Id(chatId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public MensajeDTO createMensaje(MensajeDTO dto) {
        log.info("Creando mensaje para chat ID: {} de usuario ID: {}", dto.getChatId(), dto.getUserId());

        Chat chat = chatRepository.findById(dto.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        Usuario user = usuarioRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setUser(user);
        mensaje.setTexto(dto.getTexto());

        Mensaje saved = mensajeRepository.save(mensaje);
        log.info("Mensaje guardado con ID: {}", saved.getId());

        // Crear notificaciones para los otros participantes del chat
        List<UsuarioChat> participantes = usuarioChatRepository.findByChatId(chat.getId());
        log.info("Encontrados {} participantes en el chat", participantes.size());

        for (UsuarioChat participante : participantes) {
            // No notificar al remitente del mensaje
            if (!participante.getUser().getId().equals(user.getId())) {
                Notificacion notificacion = new Notificacion();
                notificacion.setUser(participante.getUser());
                notificacion.setTipo(TipoNotificacion.nuevo_mensaje);
                notificacion.setMensaje("Nuevo mensaje de " + user.getNombre() + " " + user.getApellidos());
                notificacion.setEntidadId(chat.getId());
                notificacion.setEntidadTipo("chat");
                notificacion.setLeida(false);
                notificacion.setFecha(java.time.Instant.now());

                Notificacion notifSaved = notificacionRepository.save(notificacion);
                log.info("Notificación creada con ID: {} para usuario ID: {}", notifSaved.getId(), notifSaved.getUser().getId());
            }
        }

        return toDTO(saved);
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
