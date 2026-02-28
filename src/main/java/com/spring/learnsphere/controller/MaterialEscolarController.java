package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.MaterialEscolar;
import com.spring.learnsphere.service.MaterialEscolarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/materiales-escolares")
public class MaterialEscolarController {

    private final MaterialEscolarService materialEscolarService;

    @GetMapping("/listar")
    public List<MaterialEscolar> listarMaterialesEscolares() {
        return materialEscolarService.findAllMaterialesEscolares();
    }

    @GetMapping("/buscar/{id}")
    public MaterialEscolar buscarMaterialEscolarPorId(@PathVariable Integer id) {
        return materialEscolarService.findMaterialEscolarById(id);
    }

    @PostMapping("/crear")
    public MaterialEscolar crearMaterialEscolar(@RequestBody MaterialEscolar materialEscolar) {
        return materialEscolarService.createMaterialEscolar(materialEscolar);
    }

    @PutMapping("/editar/{id}")
    public MaterialEscolar editarMaterialEscolar(@PathVariable Integer id, @RequestBody MaterialEscolar materialEscolar) {
        return materialEscolarService.updateMaterialEscolar(id, materialEscolar);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMaterialEscolar(@PathVariable Integer id) {
        materialEscolarService.deleteMaterialEscolar(id);
    }

}
