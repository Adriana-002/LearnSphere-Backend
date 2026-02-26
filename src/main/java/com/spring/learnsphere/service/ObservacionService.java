package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Observacion;
import com.spring.learnsphere.repository.ObservacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObservacionService {

    private final ObservacionRepository observacionRepository;

    public List<Observacion> findAllObservaciones() {
        return observacionRepository.findAll();
    }

    public Observacion findObservacionById(Integer id) {
        return observacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Observación no encontrada"));
    }

    public Observacion createObservacion(Observacion observacion) {
        return observacionRepository.save(observacion);
    }

    public Observacion updateObservacion(Integer id, Observacion observacionDetails) {
        Observacion observacion = findObservacionById(id);

        if (observacion == null) {
            throw new IllegalArgumentException("Observación no encontrada");
        }
        observacion.setAlumno(observacionDetails.getAlumno());
        observacion.setAsignatura(observacionDetails.getAsignatura());
        observacion.setTrimestre(observacionDetails.getTrimestre());
        observacion.setMensaje(observacionDetails.getMensaje());
        observacion.setProfesor(observacionDetails.getProfesor());
        observacion.setFecha(observacionDetails.getFecha());
        return createObservacion(observacion);
    }

    public void deleteObservacion(Integer id) {
        if (findObservacionById(id) == null) {
            throw new IllegalArgumentException("Observación no encontrada");
        }
        observacionRepository.deleteById(id);
    }

}
