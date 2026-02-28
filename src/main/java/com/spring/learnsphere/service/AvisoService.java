package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Aviso;
import com.spring.learnsphere.repository.AvisoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvisoService {

    private final AvisoRepository avisoRepository;

    public List<Aviso> findAllAvisos() {
        return avisoRepository.findAll();
    }

    public Aviso findAvisoById(Integer id) {
        return avisoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aviso no encontrado"));
    }

    public Aviso createAviso(Aviso aviso) {
        return avisoRepository.save(aviso);
    }

    public Aviso updateAviso(Integer id, Aviso avisoDetails) {
        Aviso aviso = findAvisoById(id);

        if (aviso == null) {
            throw new IllegalArgumentException("Aviso no encontrado");
        }
        aviso.setTitulo(avisoDetails.getTitulo());
        aviso.setMensaje(avisoDetails.getMensaje());
        aviso.setEsImportante(avisoDetails.getEsImportante());
        aviso.setFechaPublicacion(avisoDetails.getFechaPublicacion());
        return createAviso(aviso);
    }

    public void deleteAviso(Integer id) {
        if (findAvisoById(id) == null) {
            throw new IllegalArgumentException("Aviso no encontrado");
        }
        avisoRepository.deleteById(id);
    }

}
