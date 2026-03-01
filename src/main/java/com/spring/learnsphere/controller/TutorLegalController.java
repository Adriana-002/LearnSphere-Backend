package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.TutorLegalDTO;
import com.spring.learnsphere.service.TutorLegalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutores-legales")
public class TutorLegalController {

    private final TutorLegalService tutorLegalService;

    @GetMapping("/listar")
    public List<TutorLegalDTO> listar() {
        return tutorLegalService.getAll();
    }

    @GetMapping("/buscar/{id}")
    public TutorLegalDTO buscarPorId(@PathVariable Integer id) {
        return tutorLegalService.findById(id);
    }

    @PutMapping("/editar/{id}")
    public TutorLegalDTO editar(@PathVariable Integer id, @RequestBody TutorLegalDTO dto) {
        return tutorLegalService.update(id, dto);
    }

}
