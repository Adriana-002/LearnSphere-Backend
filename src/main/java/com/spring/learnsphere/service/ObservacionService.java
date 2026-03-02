package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.ObservacionDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.Alumno;
import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.model.Observacion;
import com.spring.learnsphere.model.TutorAlumno;
import com.spring.learnsphere.repository.AsignaturaRepository;
import com.spring.learnsphere.repository.NotificacionRepository;
import com.spring.learnsphere.repository.ObservacionRepository;
import com.spring.learnsphere.repository.ProfesorRepository;
import com.spring.learnsphere.repository.AlumnoRepository;
import com.spring.learnsphere.repository.TutorAlumnoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ObservacionService {

    private final ObservacionRepository observacionRepository;
    private final AlumnoRepository alumnoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final ProfesorRepository profesorRepository;
    private final NotificacionRepository notificacionRepository;
    private final TutorAlumnoRepository tutorAlumnoRepository;

    public List<ObservacionDTO> getByAlumno(Integer alumnoId) {
        return observacionRepository.findByAlumnoId(alumnoId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ObservacionDTO createObservacion(ObservacionDTO dto) {
        log.info("Creando observación para alumno ID: {}", dto.getAlumnoId());

        Alumno alumno = alumnoRepository.findById(dto.getAlumnoId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Observacion obs = new Observacion();
        obs.setAlumno(alumno);
        obs.setMensaje(dto.getMensaje());
        obs.setTrimestre(dto.getTrimestre());
        obs.setFecha(java.time.Instant.now());

        if (dto.getAsignaturaNombre() != null) {
            obs.setAsignatura(asignaturaRepository.findByNombre(dto.getAsignaturaNombre()));
        }
        if (dto.getProfesorId() != null) {
            profesorRepository.findById(dto.getProfesorId())
                    .ifPresent(obs::setProfesor);
        }

        Observacion saved = observacionRepository.save(obs);
        log.info("Observación guardada con ID: {}", saved.getId());

        TutorAlumno tutorAlumno = tutorAlumnoRepository.findAllByAlumnoId(alumno.getId());
        if (tutorAlumno != null) {
            log.info("Tutor encontrado para alumno {}: Tutor ID {}", alumno.getId(), tutorAlumno.getTutor().getId());

            Notificacion notificacion = new Notificacion();
            notificacion.setUser(tutorAlumno.getTutor().getUsuarios());
            notificacion.setTipo(TipoNotificacion.nueva_observacion);
            notificacion.setMensaje("Nueva observación para " + alumno.getNombre() + " " + alumno.getApellidos());
            notificacion.setEntidadId(alumno.getId());
            notificacion.setEntidadTipo("alumno");
            notificacion.setLeida(false);
            notificacion.setFecha(java.time.Instant.now());

            Notificacion notifSaved = notificacionRepository.save(notificacion);
            log.info("Notificación creada con ID: {} para usuario ID: {}", notifSaved.getId(), notifSaved.getUser().getId());
        } else {
            log.warn("No se encontró tutor legal para el alumno ID: {}", alumno.getId());
        }

        return toDTO(saved);
    }

    public void deleteObservacion(Integer id) {
        observacionRepository.deleteById(id);
    }

    private ObservacionDTO toDTO(Observacion o) {
        ObservacionDTO dto = new ObservacionDTO();
        dto.setObservacionId(o.getId());
        dto.setAlumnoId(o.getAlumno().getId());
        dto.setMensaje(o.getMensaje());
        dto.setTrimestre(o.getTrimestre());
        dto.setFecha(o.getFecha() != null ? o.getFecha().toString() : null);
        if (o.getProfesor() != null) {
            dto.setProfesorId(o.getProfesor().getId());
            dto.setProfesorNombre(o.getProfesor().getUsuarios().getNombre() + " " + o.getProfesor().getUsuarios().getApellidos());
        }
        if (o.getAsignatura() != null) {
            dto.setAsignaturaId(o.getAsignatura().getId());
            dto.setAsignaturaNombre(o.getAsignatura().getNombre());
        }
        return dto;
    }

}
