package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.TutorLegalDTO;
import com.spring.learnsphere.model.TutorLegal;
import com.spring.learnsphere.repository.TutorLegalRepository;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TutorLegalService {

    private final TutorLegalRepository tutorLegalRepository;
    private final UsuarioRepository usuarioRepository;

    public List<TutorLegalDTO> getAll() {
        return tutorLegalRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TutorLegalDTO findById(Integer id) {
        return toDTO(tutorLegalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado")));
    }

    public TutorLegalDTO update(Integer id, TutorLegalDTO dto) {
        TutorLegal tutor = tutorLegalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));
        tutor.setEsAlumno(dto.getEsAlumno());
        return toDTO(tutorLegalRepository.save(tutor));
    }

    private TutorLegalDTO toDTO(TutorLegal t) {
        TutorLegalDTO dto = new TutorLegalDTO();
        dto.setUserId(t.getId());
        dto.setEsAlumno(t.getEsAlumno());
        usuarioRepository.findById(t.getId()).ifPresent(u -> {
            dto.setNombre(u.getNombre());
            dto.setApellidos(u.getApellidos());
            dto.setEmail(u.getEmail());
            dto.setTelefono(u.getTelefono());
        });
        return dto;
    }

}
