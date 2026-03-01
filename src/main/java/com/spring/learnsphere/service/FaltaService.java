package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.FaltaDTO;
import com.spring.learnsphere.model.Falta;
import com.spring.learnsphere.repository.AlumnoRepository;
import com.spring.learnsphere.repository.FaltaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FaltaService {

    private final FaltaRepository faltaRepository;
    private final AlumnoRepository alumnoRepository;

    public List<FaltaDTO> getByAlumno(Integer alumnoId) {
        return faltaRepository.findByAlumnoId(alumnoId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FaltaDTO createFalta(FaltaDTO dto) {
        Falta falta = new Falta();
        alumnoRepository.findById(dto.getAlumnoId()).ifPresent(falta::setAlumno);
        falta.setFecha(LocalDate.parse(dto.getFecha()));
        falta.setHora(dto.getHora() != null ? LocalTime.parse(dto.getHora()) : null);
        return toDTO(faltaRepository.save(falta));
    }

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
