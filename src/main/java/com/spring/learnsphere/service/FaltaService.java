package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.FaltaDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.Alumno;
import com.spring.learnsphere.model.Falta;
import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.model.TutorAlumno;
import com.spring.learnsphere.repository.AlumnoRepository;
import com.spring.learnsphere.repository.FaltaRepository;
import com.spring.learnsphere.repository.NotificacionRepository;
import com.spring.learnsphere.repository.TutorAlumnoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FaltaService {

    private final FaltaRepository faltaRepository;
    private final AlumnoRepository alumnoRepository;
    private final NotificacionRepository notificacionRepository;
    private final TutorAlumnoRepository tutorAlumnoRepository;

    public List<FaltaDTO> getByAlumno(Integer alumnoId) {
        return faltaRepository.findByAlumnoId(alumnoId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public FaltaDTO createFalta(FaltaDTO dto) {
        log.info("Creando falta para alumno ID: {}", dto.getAlumnoId());

        Alumno alumno = alumnoRepository.findById(dto.getAlumnoId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Falta falta = new Falta();
        falta.setAlumno(alumno);
        falta.setFecha(LocalDate.parse(dto.getFecha()));
        falta.setHora(dto.getHora() != null ? LocalTime.parse(dto.getHora()) : null);

        Falta saved = faltaRepository.save(falta);
        log.info("Falta guardada con ID: {}", saved.getId());

        // Crear notificación para el tutor legal del alumno
        TutorAlumno tutorAlumno = tutorAlumnoRepository.findAllByAlumnoId(alumno.getId());
        if (tutorAlumno != null) {
            log.info("Tutor encontrado para alumno {}: Tutor ID {}", alumno.getId(), tutorAlumno.getTutor().getId());

            Notificacion notificacion = new Notificacion();
            notificacion.setUser(tutorAlumno.getTutor().getUsuarios());
            notificacion.setTipo(TipoNotificacion.nueva_falta);
            notificacion.setMensaje("Nueva falta para " + alumno.getNombre() + " " + alumno.getApellidos());
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

    // ...existing code...

    public void deleteFalta(Integer id) {
        faltaRepository.deleteById(id);
    }

    private FaltaDTO toDTO(Falta f) {
        FaltaDTO dto = new FaltaDTO();
        dto.setFaltaId(f.getId());
        dto.setAlumnoId(f.getAlumno().getId());
        dto.setFecha(f.getFecha().toString());
        dto.setHora(f.getHora() != null ? f.getHora().toString() : null);
        return dto;
    }

}
