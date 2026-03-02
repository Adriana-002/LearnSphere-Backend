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

/**
 * Servicio que gestiona la lógica de negocio relacionada con los chats en el sistema LearnSphere.
 *
 * Proporciona operaciones para la creación y consulta de chats entre usuarios,
 * incluyendo la gestión de participantes y el envío de notificaciones a tutores legales.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
@Slf4j
public class ChatService {

    /** Repositorio para acceder a los datos de chats. */
    private final ChatRepository chatRepository;

    /** Repositorio para acceder a las relaciones usuario-chat. */
    private final UsuarioChatRepository usuarioChatRepository;

    /** Repositorio para acceder a los datos de mensajes. */
    private final MensajeRepository mensajeRepository;

    /** Repositorio para acceder a los datos de alumnos. */
    private final AlumnoRepository alumnoRepository;

    /** Repositorio para acceder a las relaciones tutor-alumno. */
    private final TutorAlumnoRepository tutorAlumnoRepository;

    /** Repositorio para acceder a los datos de usuarios. */
    private final UsuarioRepository usuarioRepository;

    /** Repositorio para acceder a las relaciones profesor-asignatura. */
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;

    /** Repositorio para acceder a los datos de notificaciones. */
    private final NotificacionRepository notificacionRepository;

    /**
     * Obtiene la lista de chats en los que participa un usuario.
     *
     * @param userId identificador del usuario
     * @return lista de DTOs con la información de los chats del usuario
     */
    public List<ChatDTO> getByUsuario(Integer userId) {
        List<Integer> chatIds = usuarioChatRepository.findByUserId(userId)
                .stream().map(uc -> uc.getId().getChatId()).collect(Collectors.toList());

        if (chatIds.isEmpty()) {
            return List.of();
        }

        List<Chat> chats = chatRepository.findAllById(chatIds);

        return chats.stream().map(chat -> toDTO(chat, userId)).collect(Collectors.toList());
    }

    /**
     * Crea un nuevo chat y añade a los participantes correspondientes.
     *
     * Busca el tutor legal de cada alumno seleccionado y lo añade como participante del chat.
     * Además, genera notificaciones para cada tutor añadido.
     *
     * @param dto datos del chat a crear, incluyendo el creador y los IDs de los alumnos
     * @return DTO con la información del chat creado
     * @throws RuntimeException si el usuario creador o algún alumno no existe
     */
    @Transactional
    public ChatDTO createChat(ChatDTO dto) {
        log.info("Creando chat de tipo: {}", dto.getTipo());

        Chat chat = new Chat();
        chat.setTipo(dto.getTipo());
        Chat saved = chatRepository.save(chat);
        log.info("Chat guardado con ID: {}", saved.getId());

        Usuario creador = null;
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

    /**
     * Convierte una entidad Chat a su correspondiente DTO.
     *
     * Incluye información del último mensaje, nombre del chat y nombre de la asignatura
     * en caso de chats individuales.
     *
     * @param chat   entidad Chat a convertir
     * @param userId identificador del usuario actual para determinar el otro participante
     * @return DTO con la información del chat
     */
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

                        List<TutorAlumno> tutorAlumnos = tutorAlumnoRepository.findAllByTutor_Id(uc.getUser().getId());
                        if (!tutorAlumnos.isEmpty()) {
                            Alumno alumno = tutorAlumnos.get(0).getAlumno();
                            dto.setNombreAlumno(alumno.getNombre() + " " + alumno.getApellidos());
                        }
                    });

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


    /**
     * Calcula el tiempo transcurrido desde una fecha dada en formato legible.
     *
     * @param fecha fecha de referencia para el cálculo
     * @return cadena con el tiempo transcurrido (minutos, horas o días)
     */
    private String calcularTiempo(LocalDateTime fecha) {
        long minutos = ChronoUnit.MINUTES.between(fecha, LocalDateTime.now());
        if (minutos < 60) return minutos + "min";
        long horas = ChronoUnit.HOURS.between(fecha, LocalDateTime.now());
        if (horas < 24) return horas + "h";
        return ChronoUnit.DAYS.between(fecha, LocalDateTime.now()) + "d";
    }

}
