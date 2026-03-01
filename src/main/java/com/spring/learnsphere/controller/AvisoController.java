package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AvisoDTO;
import com.spring.learnsphere.service.AvisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/avisos")
public class AvisoController {

    private final AvisoService avisoService;

    @GetMapping("/listar")
    public List<AvisoDTO> getAllAvisos() {
        return avisoService.getAllAvisos();
    }

    @GetMapping("/importantes")
    public List<AvisoDTO> getImportantes() {
        return avisoService.getImportantes();
    }

    @PostMapping("/crear")
    public AvisoDTO createAviso(@RequestBody AvisoDTO dto) {
        return avisoService.createAviso(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteAviso(@PathVariable Integer id) {
        avisoService.deleteAviso(id);
    }

}
