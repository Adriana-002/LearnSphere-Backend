package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.NotaDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.Alumno;
import com.spring.learnsphere.model.Asignatura;
import com.spring.learnsphere.model.CursoAsignatura;
import com.spring.learnsphere.model.Nota;
import com.spring.learnsphere.model.Notificacion;
import com.spring.learnsphere.model.TutorAlumno;
import com.spring.learnsphere.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class NotaService {

    private final NotaRepository notaRepository;
    private final AlumnoRepository alumnoRepository;
    private final CursoAsignaturaRepository cursoAsignaturaRepository;
    private final AlumnoCursoRepository alumnoCursoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final NotificacionRepository notificacionRepository;
    private final TutorAlumnoRepository tutorAlumnoRepository;

    public List<NotaDTO> getByAlumnoYTrimestre(Integer alumnoId, Integer trimestre) {
        return notaRepository.findByAlumno_IdAndTrimestre(alumnoId, trimestre)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<NotaDTO> getByAlumno(Integer alumnoId) {
        return notaRepository.findByAlumno_Id(alumnoId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public NotaDTO createNota(NotaDTO dto) {
        log.info("Creando nota para alumno ID: {}", dto.getAlumnoId());

        Alumno alumno = alumnoRepository.findById(dto.getAlumnoId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        CursoAsignatura cursoAsignatura;

        if (dto.getCursoAsignaturaId() != null) {
            cursoAsignatura = cursoAsignaturaRepository.findById(dto.getCursoAsignaturaId())
                    .orElseThrow(() -> new RuntimeException("CursoAsignatura no encontrado"));
        } else if (dto.getAsignaturaNombre() != null) {
            Asignatura asignatura = asignaturaRepository.findByNombre(dto.getAsignaturaNombre());
            if (asignatura == null) {
                throw new RuntimeException("Asignatura no encontrada: " + dto.getAsignaturaNombre());
            }

            Integer cursoId = alumnoCursoRepository.findByAlumnoId(alumno.getId())
                    .orElseThrow(() -> new RuntimeException("El alumno no tiene curso asignado"))
                    .getCurso().getId();

            cursoAsignatura = cursoAsignaturaRepository.findByCursoId(cursoId).stream()
                    .filter(ca -> ca.getAsignatura().getId().equals(asignatura.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("La asignatura no pertenece al curso del alumno"));
        } else {
            throw new RuntimeException("Debe proporcionar cursoAsignaturaId o asignaturaNombre");
        }

        Nota nota = new Nota();
        nota.setAlumno(alumno);
        nota.setCursoAsignatura(cursoAsignatura);
        nota.setTrimestre(dto.getTrimestre());
        nota.setCalificacion(dto.getCalificacion() != null ? BigDecimal.valueOf(dto.getCalificacion()) : null);
        nota.setFechaRegistro(java.time.LocalDate.now());

        Nota saved = notaRepository.save(nota);
        log.info("Nota guardada con ID: {}", saved.getId());

        // Crear notificación para el tutor legal del alumno
        TutorAlumno tutorAlumno = tutorAlumnoRepository.findAllByAlumnoId(alumno.getId());
        if (tutorAlumno != null) {
            log.info("Tutor encontrado para alumno {}: Tutor ID {}", alumno.getId(), tutorAlumno.getTutor().getId());

            Notificacion notificacion = new Notificacion();
            notificacion.setUser(tutorAlumno.getTutor().getUsuarios());
            notificacion.setTipo(TipoNotificacion.nueva_nota);
            notificacion.setMensaje("Nueva nota en " + cursoAsignatura.getAsignatura().getNombre() +
                                   " para " + alumno.getNombre() + " " + alumno.getApellidos() +
                                   " - Trimestre " + dto.getTrimestre());
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

    public NotaDTO updateNota(Integer id, NotaDTO dto) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
        nota.setCalificacion(dto.getCalificacion() != null ? BigDecimal.valueOf(dto.getCalificacion()) : null);

        Nota saved = notaRepository.save(nota);

        TutorAlumno tutorAlumno = tutorAlumnoRepository.findAllByAlumnoId(nota.getAlumno().getId());
        if (tutorAlumno != null) {
            Notificacion notificacion = new Notificacion();
            notificacion.setUser(tutorAlumno.getTutor().getUsuarios());
            notificacion.setTipo(TipoNotificacion.nueva_nota);
            notificacion.setMensaje("La nota de " + nota.getAlumno().getNombre() + " ha sido actualizada.");
            notificacion.setEntidadId(nota.getAlumno().getId());
            notificacion.setEntidadTipo("nota");
            notificacion.setLeida(false);
            notificacion.setFecha(java.time.Instant.now());
            notificacionRepository.save(notificacion);
        }

        return toDTO(saved);
    }


    private NotaDTO toDTO(Nota n) {
        NotaDTO dto = new NotaDTO();
        dto.setNotaId(n.getId());
        dto.setAlumnoId(n.getAlumno().getId());
        dto.setTrimestre(n.getTrimestre());
        dto.setCalificacion(n.getCalificacion() != null ? n.getCalificacion().doubleValue() : null);
        dto.setFechaRegistro(n.getFechaRegistro() != null ? n.getFechaRegistro().toString() : null);
        dto.setCursoAsignaturaId(n.getCursoAsignatura().getId());
        dto.setAsignaturaNombre(n.getCursoAsignatura().getAsignatura().getNombre());
        return dto;
    }

}
