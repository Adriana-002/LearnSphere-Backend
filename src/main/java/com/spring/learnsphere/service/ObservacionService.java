package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.ObservacionDTO;
import com.spring.learnsphere.model.Alumno;
import com.spring.learnsphere.model.Observacion;
import com.spring.learnsphere.repository.AsignaturaRepository;
import com.spring.learnsphere.repository.ObservacionRepository;
import com.spring.learnsphere.repository.ProfesorRepository;
import com.spring.learnsphere.repository.AlumnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ObservacionService {

    private final ObservacionRepository observacionRepository;
    private final AlumnoRepository alumnoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final ProfesorRepository profesorRepository;

    public List<ObservacionDTO> getByAlumno(Integer alumnoId) {
        return observacionRepository.findByAlumnoId(alumnoId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ObservacionDTO createObservacion(ObservacionDTO dto) {
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

        return toDTO(observacionRepository.save(obs));
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
