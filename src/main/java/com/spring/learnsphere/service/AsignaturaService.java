package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.AsignaturaDTO;
import com.spring.learnsphere.model.Asignatura;
import com.spring.learnsphere.repository.AsignaturaRepository;
import com.spring.learnsphere.repository.CursoAsignaturaRepository;
import com.spring.learnsphere.repository.ProfesorAsignaturaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final CursoAsignaturaRepository cursoAsignaturaRepository;
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;

    public List<AsignaturaDTO> getByCurso(Integer cursoId) {
        return cursoAsignaturaRepository.findAll().stream()
                .filter(ca -> ca.getCurso().getId().equals(cursoId))
                .map(ca -> toDTO(ca.getAsignatura()))
                .collect(Collectors.toList());
    }

    public List<AsignaturaDTO> getByProfesor(Integer profesorId) {
        return profesorAsignaturaRepository.findByProfesor_Id(profesorId).stream()
                .map(pa -> toDTO(pa.getAsignatura()))
                .collect(Collectors.toList());
    }

    public AsignaturaDTO createAsignatura(AsignaturaDTO dto) {
        Asignatura a = new Asignatura();
        a.setNombre(dto.getNombre());
        return toDTO(asignaturaRepository.save(a));
    }

    public AsignaturaDTO updateAsignatura(Integer id, AsignaturaDTO dto) {
        Asignatura a = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        a.setNombre(dto.getNombre());
        return toDTO(asignaturaRepository.save(a));
    }

    private AsignaturaDTO toDTO(Asignatura a) {
        AsignaturaDTO dto = new AsignaturaDTO();
        dto.setAsignaturaId(a.getId());
        dto.setNombre(a.getNombre());
        return dto;
    }

}
