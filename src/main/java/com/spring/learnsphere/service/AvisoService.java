package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.AvisoDTO;
import com.spring.learnsphere.model.Aviso;
import com.spring.learnsphere.repository.AvisoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AvisoService {

    private final AvisoRepository avisoRepository;

    public List<AvisoDTO> getAllAvisos() {
        return avisoRepository.findAllByOrderByFechaPublicacionDesc()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AvisoDTO> getImportantes() {
        return avisoRepository.findByEsImportanteTrue()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AvisoDTO createAviso(AvisoDTO dto) {
        Aviso aviso = new Aviso();
        aviso.setTitulo(dto.getTitulo());
        aviso.setMensaje(dto.getMensaje());
        aviso.setEsImportante(dto.getEsImportante());
        return toDTO(avisoRepository.save(aviso));
    }

    public void deleteAviso(Integer id) {
        avisoRepository.deleteById(id);
    }

    private AvisoDTO toDTO(Aviso a) {
        AvisoDTO dto = new AvisoDTO();
        dto.setAvisoId(a.getId());
        dto.setTitulo(a.getTitulo());
        dto.setMensaje(a.getMensaje());
        dto.setEsImportante(a.getEsImportante());
        dto.setFechaPublicacion(a.getFechaPublicacion() != null ? a.getFechaPublicacion().toString() : null);
        return dto;
    }

}
