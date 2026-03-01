package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.MaterialEscolarDTO;
import com.spring.learnsphere.model.Curso;
import com.spring.learnsphere.model.CursoMaterial;
import com.spring.learnsphere.model.CursoMaterialId;
import com.spring.learnsphere.model.MaterialEscolar;
import com.spring.learnsphere.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialEscolarService {

    private final MaterialEscolarRepository materialRepository;
    private final CursoMaterialRepository cursoMaterialRepository;
    private final CursoRepository cursoRepository;
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;
    private final CursoAsignaturaRepository cursoAsignaturaRepository;

    public List<MaterialEscolarDTO> getAll() {
        return materialRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MaterialEscolarDTO> getByCurso(Integer cursoId) {
        return cursoMaterialRepository.findAll().stream()
                .filter(cm -> cm.getCurso().getId().equals(cursoId))
                .map(cm -> toDTO(cm.getMaterial()))
                .collect(Collectors.toList());
    }

    public List<MaterialEscolarDTO> getByProfesor(Integer profesorId) {
        List<Integer> cursoIds = profesorAsignaturaRepository.findByProfesor_Id(profesorId)
                .stream()
                .flatMap(pa -> cursoAsignaturaRepository.findAll().stream()
                        .filter(ca -> ca.getAsignatura().getId().equals(pa.getAsignatura().getId()))
                        .map(ca -> ca.getCurso().getId()))
                .distinct()
                .collect(Collectors.toList());

        return cursoMaterialRepository.findAll().stream()
                .filter(cm -> cursoIds.contains(cm.getCurso().getId()))
                .map(cm -> toDTO(cm.getMaterial()))
                .distinct()
                .collect(Collectors.toList());
    }

    public MaterialEscolarDTO createMaterial(MaterialEscolarDTO dto) {
        MaterialEscolar m = new MaterialEscolar();
        m.setNombre(dto.getNombre());
        m.setEditorial(dto.getEditorial());
        m.setIsbn(dto.getIsbn());
        MaterialEscolar saved = materialRepository.save(m);

        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

            CursoMaterialId cmId = new CursoMaterialId();
            cmId.setCursoId(curso.getId());
            cmId.setMaterialId(saved.getId());

            CursoMaterial cm = new CursoMaterial();
            cm.setId(cmId);
            cm.setCurso(curso);
            cm.setMaterial(saved);
            cursoMaterialRepository.save(cm);
        }

        MaterialEscolarDTO result = toDTO(saved);
        result.setCursoId(dto.getCursoId());
        return result;
    }

    private MaterialEscolarDTO toDTO(MaterialEscolar m) {
        MaterialEscolarDTO dto = new MaterialEscolarDTO();
        dto.setMaterialId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setEditorial(m.getEditorial());
        dto.setIsbn(m.getIsbn());
        return dto;
    }


}
