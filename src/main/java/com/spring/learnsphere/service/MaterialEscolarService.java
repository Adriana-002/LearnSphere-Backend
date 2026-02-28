package com.spring.learnsphere.service;

import com.spring.learnsphere.model.MaterialEscolar;
import com.spring.learnsphere.repository.MaterialEscolarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaterialEscolarService {
    @Autowired
    private MaterialEscolarRepository materialEscolarRepository;

    public List<MaterialEscolar> listarTodos() {
        return materialEscolarRepository.findAll();
    }

    public Optional<MaterialEscolar> obtenerPorId(Integer id) {
        return materialEscolarRepository.findById(id);
    }

    public MaterialEscolar guardar(MaterialEscolar material) {
        return materialEscolarRepository.save(material);
    }

    public void eliminar(Integer id) {
        materialEscolarRepository.deleteById(id);
    }
}
