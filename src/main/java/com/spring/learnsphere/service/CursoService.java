package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.CursoDTO;
import com.spring.learnsphere.model.Curso;
import com.spring.learnsphere.repository.AlumnoCursoRepository;
import com.spring.learnsphere.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final AlumnoCursoRepository alumnoCursoRepository;

    public List<CursoDTO> getAll() {
        return cursoRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CursoDTO findById(Integer id) {
        return toDTO(cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));
    }

    public CursoDTO getByAlumno(Integer alumnoId) {
        return alumnoCursoRepository.findAll().stream()
                .filter(ac -> ac.getAlumno().getId().equals(alumnoId))
                .findFirst()
                .map(ac -> toDTO(ac.getCurso()))
                .orElseThrow(() -> new RuntimeException("Curso no encontrado para este alumno"));
    }

    public CursoDTO create(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());
        curso.setAñoAcademico(dto.getAñoAcademico());
        return toDTO(cursoRepository.save(curso));
    }

    public CursoDTO update(Integer id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setNombre(dto.getNombre());
        curso.setAñoAcademico(dto.getAñoAcademico());
        return toDTO(cursoRepository.save(curso));
    }

    public void delete(Integer id) {
        cursoRepository.deleteById(id);
    }

    private CursoDTO toDTO(Curso c) {
        CursoDTO dto = new CursoDTO();
        dto.setCursoId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setAñoAcademico(c.getAñoAcademico());
        return dto;
    }

}
