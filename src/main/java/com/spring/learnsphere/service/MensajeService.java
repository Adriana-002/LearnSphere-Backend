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

/**
 * Servicio que gestiona la lógica de negocio relacionada con los mensajes en el sistema LearnSphere.
 *
 * Proporciona operaciones para la creación, consulta y eliminación de mensajes dentro de chats,
 * incluyendo la generación de notificaciones automáticas para los participantes del chat.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
@Slf4j
public class MensajeService {

    /** Repositorio para acceder a los datos de mensajes. */
    private final MensajeRepository mensajeRepository;

    /** Repositorio para acceder a los datos de chats. */
    private final ChatRepository chatRepository;

    /** Repositorio para acceder a los datos de usuarios. */
    private final UsuarioRepository usuarioRepository;

    /** Repositorio para acceder a los datos de notificaciones. */
    private final NotificacionRepository notificacionRepository;

    /** Repositorio para acceder a las relaciones usuario-chat. */
    private final UsuarioChatRepository usuarioChatRepository;

    /**
     * Obtiene la lista de mensajes de un chat.
     *
     * @param chatId identificador del chat
     * @return lista de DTOs con la información de los mensajes del chat
     */
    public List<MensajeDTO> getByChatId(Integer chatId) {
        return mensajeRepository.findByChat_Id(chatId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Crea un nuevo mensaje dentro de un chat y genera notificaciones para los demás participantes.
     *
     * @param dto datos del mensaje a crear
     * @return DTO con la información del mensaje creado
     * @throws RuntimeException si el chat o el usuario no existen
     */
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

        List<UsuarioChat> participantes = usuarioChatRepository.findByChatId(chat.getId());
        log.info("Encontrados {} participantes en el chat", participantes.size());

        for (UsuarioChat participante : participantes) {
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

    /**
     * Elimina un mensaje del sistema.
     *
     * @param id identificador del mensaje a eliminar
     */
    public void deleteMensaje(Integer id) {
        mensajeRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Mensaje a su correspondiente DTO.
     *
     * @param m entidad Mensaje a convertir
     * @return DTO con la información del mensaje
     */
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
