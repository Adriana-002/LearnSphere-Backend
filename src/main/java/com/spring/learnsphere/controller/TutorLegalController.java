package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.TutorLegal;
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
    public List<TutorLegal> listarTutoresLegales() {
        return tutorLegalService.findAllTutoresLegales();
    }

    @GetMapping("/buscar/{id}")
    public TutorLegal buscarTutorLegalPorId(@PathVariable Integer id) {
        return tutorLegalService.findTutorLegalById(id);
    }

    @PostMapping("/crear")
    public TutorLegal crearTutorLegal(@RequestBody TutorLegal tutorLegal) {
        return tutorLegalService.createTutorLegal(tutorLegal);
    }

    @PutMapping("/editar/{id}")
    public TutorLegal editarTutorLegal(@PathVariable Integer id, @RequestBody TutorLegal tutorLegal) {
        return tutorLegalService.updateTutorLegal(id, tutorLegal);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarTutorLegal(@PathVariable Integer id) {
        tutorLegalService.deleteTutorLegal(id);
    }

}
