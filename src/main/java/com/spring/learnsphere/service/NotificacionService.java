package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.NotificacionDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.repository.NotificacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public List<NotificacionDTO> getByUsuario(Integer userId) {
        return notificacionRepository.findByUser_IdOrderByFechaDesc(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<NotificacionDTO> getImportantesByUsuario(Integer userId) {
        List<TipoNotificacion> importantes = List.of(
                TipoNotificacion.nueva_nota,
                TipoNotificacion.nueva_falta,
                TipoNotificacion.nueva_observacion
        );
        return notificacionRepository.findByUser_IdAndTipoInOrderByFechaDesc(userId, importantes)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void marcarLeida(Integer id) {
        Notificacion n = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        n.setLeida(true);
        notificacionRepository.save(n);
    }

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
