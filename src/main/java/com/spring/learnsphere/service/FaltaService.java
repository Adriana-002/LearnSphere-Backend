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

/**
 * Servicio que gestiona la lógica de negocio relacionada con las faltas de asistencia en el sistema LearnSphere.
 *
 * Proporciona operaciones para la creación, consulta y eliminación de faltas,
 * incluyendo la generación de notificaciones automáticas para los tutores legales.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
@Slf4j
public class FaltaService {

    /** Repositorio para acceder a los datos de faltas. */
    private final FaltaRepository faltaRepository;

    /** Repositorio para acceder a los datos de alumnos. */
    private final AlumnoRepository alumnoRepository;

    /** Repositorio para acceder a los datos de notificaciones. */
    private final NotificacionRepository notificacionRepository;

    /** Repositorio para acceder a las relaciones tutor-alumno. */
    private final TutorAlumnoRepository tutorAlumnoRepository;

    /**
     * Obtiene la lista de faltas de un alumno.
     *
     * @param alumnoId identificador del alumno
     * @return lista de DTOs con la información de las faltas del alumno
     */
    public List<FaltaDTO> getByAlumno(Integer alumnoId) {
        return faltaRepository.findByAlumnoId(alumnoId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Crea una nueva falta de asistencia y genera una notificación para el tutor legal del alumno.
     *
     * @param dto datos de la falta a crear
     * @return DTO con la información de la falta creada
     * @throws RuntimeException si el alumno no existe
     */
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

    /**
     * Elimina una falta de asistencia del sistema.
     *
     * @param id identificador de la falta a eliminar
     */
    public void deleteFalta(Integer id) {
        faltaRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Falta a su correspondiente DTO.
     *
     * @param f entidad Falta a convertir
     * @return DTO con la información de la falta
     */
    private FaltaDTO toDTO(Falta f) {
        FaltaDTO dto = new FaltaDTO();
        dto.setFaltaId(f.getId());
        dto.setAlumnoId(f.getAlumno().getId());
        dto.setFecha(f.getFecha().toString());
        dto.setHora(f.getHora() != null ? f.getHora().toString() : null);
        return dto;
    }

}
