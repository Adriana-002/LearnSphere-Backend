package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.ProfesorDTO;
import com.spring.learnsphere.model.Profesor;
import com.spring.learnsphere.repository.ProfesorRepository;
import com.spring.learnsphere.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final UsuarioRepository usuarioRepository;

    public List<ProfesorDTO> getAll() {
        return profesorRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProfesorDTO findById(Integer id) {
        return toDTO(profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado")));
    }

    public ProfesorDTO update(Integer id, ProfesorDTO dto) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        profesor.setDepartamento(dto.getDepartamento());
        return toDTO(profesorRepository.save(profesor));
    }

    private ProfesorDTO toDTO(Profesor p) {
        ProfesorDTO dto = new ProfesorDTO();
        dto.setUserId(p.getId());
        dto.setDepartamento(p.getDepartamento());
        usuarioRepository.findById(p.getId()).ifPresent(u -> {
            dto.setNombre(u.getNombre());
            dto.setApellidos(u.getApellidos());
            dto.setEmail(u.getEmail());
        });
        return dto;
    }

}
