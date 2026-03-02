package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.ChatDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.*;
import com.spring.learnsphere.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;
    private final UsuarioChatRepository usuarioChatRepository;
    private final MensajeRepository mensajeRepository;
    private final AlumnoRepository alumnoRepository;
    private final TutorAlumnoRepository tutorAlumnoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;
    private final NotificacionRepository notificacionRepository;


    public List<ChatDTO> getByUsuario(Integer userId) {
        List<Integer> chatIds = usuarioChatRepository.findByUserId(userId)
                .stream().map(uc -> uc.getId().getChatId()).collect(Collectors.toList());

        if (chatIds.isEmpty()) {
            return List.of();
        }

        List<Chat> chats = chatRepository.findAllById(chatIds);

        return chats.stream().map(chat -> toDTO(chat, userId)).collect(Collectors.toList());
    }

    @Transactional
    public ChatDTO createChat(ChatDTO dto) {
        log.info("Creando chat de tipo: {}", dto.getTipo());

        Chat chat = new Chat();
        chat.setTipo(dto.getTipo());
        Chat saved = chatRepository.save(chat);
        log.info("Chat guardado con ID: {}", saved.getId());

        Usuario creador = null;
        // Añadir al creador del chat como participante
        if (dto.getCreadorId() != null) {
            creador = usuarioRepository.findById(dto.getCreadorId())
                    .orElseThrow(() -> new RuntimeException("Usuario creador no encontrado: " + dto.getCreadorId()));

            UsuarioChatId ucCreadorId = new UsuarioChatId();
            ucCreadorId.setChatId(saved.getId());
            ucCreadorId.setUserId(creador.getId());

            UsuarioChat ucCreador = new UsuarioChat();
            ucCreador.setId(ucCreadorId);
            ucCreador.setChat(saved);
            ucCreador.setUser(creador);
            usuarioChatRepository.save(ucCreador);
        }

        // Por cada alumno seleccionado, buscar su tutor legal y añadirlo al chat
        int notificacionesCreadas = 0;
        if (dto.getUserIds() != null && !dto.getUserIds().isEmpty()) {
            log.info("Añadiendo {} alumnos al chat", dto.getUserIds().size());

            for (Integer alumnoId : dto.getUserIds()) {
                Alumno alumno = alumnoRepository.findById(alumnoId)
                        .orElseThrow(() -> new RuntimeException("Alumno no encontrado: " + alumnoId));

                TutorAlumno tutorAlumno = tutorAlumnoRepository.findAllByAlumnoId(alumno.getId());
                if (tutorAlumno == null) {
                    log.warn("No se encontró tutor legal para el alumno ID: {}", alumnoId);
                    throw new RuntimeException("Tutor legal no encontrado para el alumno: " + alumnoId);
                }

                Usuario tutorUsuario = tutorAlumno.getTutor().getUsuarios();

                UsuarioChatId ucId = new UsuarioChatId();
                ucId.setChatId(saved.getId());
                ucId.setUserId(tutorUsuario.getId());

                UsuarioChat uc = new UsuarioChat();
                uc.setId(ucId);
                uc.setChat(saved);
                uc.setUser(tutorUsuario);
                usuarioChatRepository.save(uc);

                // Crear notificación para el tutor
                if (creador != null) {
                    Notificacion notificacion = new Notificacion();
                    notificacion.setUser(tutorUsuario);
                    notificacion.setTipo(TipoNotificacion.nuevo_mensaje);
                    notificacion.setMensaje("Nuevo chat iniciado por " + creador.getNombre() + " " + creador.getApellidos() +
                                           " sobre " + alumno.getNombre() + " " + alumno.getApellidos());
                    notificacion.setEntidadId(saved.getId());
                    notificacion.setEntidadTipo("chat");
                    notificacion.setLeida(false);
                    notificacion.setFecha(java.time.Instant.now());

                    Notificacion notifSaved = notificacionRepository.save(notificacion);
                    notificacionesCreadas++;
                    log.info("Notificación creada con ID: {} para tutor de alumno {}",
                            notifSaved.getId(), alumno.getNombre());
                }
            }
        }

        log.info("Se crearon {} notificaciones para el chat {}", notificacionesCreadas, saved.getId());

        ChatDTO result = toDTO(saved, dto.getCreadorId());
        result.setUserIds(dto.getUserIds());
        result.setCreadorId(dto.getCreadorId());
        return result;
    }

    private ChatDTO toDTO(Chat chat, Integer userId) {
        ChatDTO dto = new ChatDTO();
        dto.setChatId(chat.getId());
        dto.setTipo(chat.getTipo());

        mensajeRepository.findTopByChat_IdOrderByFechaEnvioDesc(chat.getId())
                .ifPresent(m -> {
                    dto.setUltimoMensaje(m.getTexto());
                    if (m.getFechaEnvio() != null) {
                        dto.setTiempoUltimoMensaje(calcularTiempo(m.getFechaEnvio()));
                    }
                });

        if ("individual".equals(chat.getTipo()) && userId != null) {
            usuarioChatRepository.findByChatIdAndUserIdNot(chat.getId(), userId)
                    .ifPresent(uc -> {
                        dto.setNombreChat(uc.getUser().getNombre() + " " + uc.getUser().getApellidos());

                        // Buscar si este usuario es tutor legal y obtener el nombre del alumno
                        List<TutorAlumno> tutorAlumnos = tutorAlumnoRepository.findAllByTutor_Id(uc.getUser().getId());
                        if (!tutorAlumnos.isEmpty()) {
                            Alumno alumno = tutorAlumnos.get(0).getAlumno();
                            dto.setNombreAlumno(alumno.getNombre() + " " + alumno.getApellidos());
                        }
                    });

            // Buscar entre los participantes del chat cuál es profesor y obtener su asignatura
            List<UsuarioChat> participantes = usuarioChatRepository.findByChatId(chat.getId());
            for (UsuarioChat participante : participantes) {
                List<ProfesorAsignatura> profAsignaturas = profesorAsignaturaRepository.findByProfesor_Id(participante.getUser().getId());
                if (!profAsignaturas.isEmpty()) {
                    dto.setNombreAsignatura(profAsignaturas.get(0).getAsignatura().getNombre());
                    break;
                }
            }
        } else {
            dto.setNombreChat("Chat grupal " + chat.getId());
        }
        return dto;
    }


    private String calcularTiempo(LocalDateTime fecha) {
        long minutos = ChronoUnit.MINUTES.between(fecha, LocalDateTime.now());
        if (minutos < 60) return minutos + "min";
        long horas = ChronoUnit.HOURS.between(fecha, LocalDateTime.now());
        if (horas < 24) return horas + "h";
        return ChronoUnit.DAYS.between(fecha, LocalDateTime.now()) + "d";
    }

}
