package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.NotificacionDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.repository.NotificacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con las notificaciones en el sistema LearnSphere.
 *
 * Proporciona operaciones para la consulta y gestión de notificaciones de los usuarios,
 * incluyendo el filtrado de notificaciones importantes y el marcado como leídas.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
public class NotificacionService {

    /** Repositorio para acceder a los datos de notificaciones. */
    private final NotificacionRepository notificacionRepository;

    /**
     * Obtiene la lista de notificaciones de un usuario ordenadas por fecha descendente.
     *
     * @param userId identificador del usuario
     * @return lista de DTOs con la información de las notificaciones del usuario
     */
    public List<NotificacionDTO> getByUsuario(Integer userId) {
        return notificacionRepository.findByUser_IdOrderByFechaDesc(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Obtiene las notificaciones importantes de un usuario.
     *
     * Filtra las notificaciones por los tipos considerados importantes:
     * nueva nota, nueva falta y nueva observación.
     *
     * @param userId identificador del usuario
     * @return lista de DTOs con las notificaciones importantes del usuario
     */
    public List<NotificacionDTO> getImportantesByUsuario(Integer userId) {
        List<TipoNotificacion> importantes = List.of(
                TipoNotificacion.nueva_nota,
                TipoNotificacion.nueva_falta,
                TipoNotificacion.nueva_observacion
        );
        return notificacionRepository.findByUser_IdAndTipoInOrderByFechaDesc(userId, importantes)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Marca una notificación como leída.
     *
     * @param id identificador de la notificación a marcar
     * @throws RuntimeException si la notificación no existe
     */
    public void marcarLeida(Integer id) {
        Notificacion n = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        n.setLeida(true);
        notificacionRepository.save(n);
    }

    /**
     * Convierte una entidad Notificacion a su correspondiente DTO.
     *
     * @param n entidad Notificacion a convertir
     * @return DTO con la información de la notificación
     */
    private NotificacionDTO toDTO(Notificacion n) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setNotificacionId(n.getId());
        dto.setUserId(n.getUser().getId());
        dto.setTipo(n.getTipo().name());
        dto.setMensaje(n.getMensaje());
        dto.setEntidadId(n.getEntidadId());
        dto.setEntidadTipo(n.getEntidadTipo());
        dto.setLeida(n.getLeida());
        dto.setFecha(n.getFecha() != null ? n.getFecha().toString() : null);
        return dto;
    }

}
