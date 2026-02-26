package com.spring.learnsphere.service;

import com.spring.learnsphere.model.MaterialEscolar;
import com.spring.learnsphere.repository.MaterialEscolarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialEscolarService {

    private final MaterialEscolarRepository materialEscolarRepository;

    public List<MaterialEscolar> findAllMaterialesEscolares() {
        return materialEscolarRepository.findAll();
    }

    public MaterialEscolar findMaterialEscolarById(Integer id) {
        return materialEscolarRepository.findById(id).orElseThrow(() -> new RuntimeException("Material escolar no encontrado"));
    }

    public MaterialEscolar createMaterialEscolar(MaterialEscolar materialEscolar) {
        return materialEscolarRepository.save(materialEscolar);
    }

    public MaterialEscolar updateMaterialEscolar(Integer id, MaterialEscolar materialEscolarDetails) {
        MaterialEscolar materialEscolar = findMaterialEscolarById(id);

        if (materialEscolar == null) {
            throw new IllegalArgumentException("Material escolar no encontrado");
        }
        materialEscolar.setNombre(materialEscolarDetails.getNombre());
        return createMaterialEscolar(materialEscolar);
    }

    public void deleteMaterialEscolar(Integer id) {
        if (findMaterialEscolarById(id) == null) {
            throw new IllegalArgumentException("Material escolar no encontrado");
        }
        materialEscolarRepository.deleteById(id);
    }

}
