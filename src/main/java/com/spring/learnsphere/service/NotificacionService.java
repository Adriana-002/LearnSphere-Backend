package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.repository.NotificacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public List<Notificacion> findAllNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion findNotificacionById(Integer id) {
        return notificacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    public Notificacion createNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Notificacion updateNotificacion(Integer id, Notificacion notificacionDetails) {
        Notificacion notificacion = findNotificacionById(id);

        if (notificacion == null) {
            throw new IllegalArgumentException("Notificación no encontrada");
        }
        notificacion.setUser(notificacionDetails.getUser());
        notificacion.setTipo(notificacionDetails.getTipo());
        notificacion.setMensaje(notificacionDetails.getMensaje());
        notificacion.setEntidadId(notificacionDetails.getEntidadId());
        notificacion.setEntidadTipo(notificacionDetails.getEntidadTipo());
        notificacion.setLeida(notificacionDetails.getLeida());
        notificacion.setFecha(notificacionDetails.getFecha());
        return createNotificacion(notificacion);
    }

    public void deleteNotificacion(Integer id) {
        if (findNotificacionById(id) == null) {
            throw new IllegalArgumentException("Notificación no encontrada");
        }
        notificacionRepository.deleteById(id);
    }

}
