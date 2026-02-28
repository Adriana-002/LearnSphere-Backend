package com.spring.learnsphere.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.spring.learnsphere.model.Aviso;
import com.spring.learnsphere.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AvisoService {
    @Autowired
    private AvisoRepository avisoRepository;

    public List<Aviso> listarTodos() {
        return avisoRepository.findAll();
    }

    public Optional<Aviso> obtenerPorId(Integer id) {
        return avisoRepository.findById(id);
    }

    public Aviso guardar(Aviso aviso) {
        if (aviso.getFechaPublicacion() == null) {
            aviso.setFechaPublicacion(Instant.now());
        }
        return avisoRepository.save(aviso);
    }

    public void eliminar(Integer id) {
        avisoRepository.deleteById(id);
    }
}
