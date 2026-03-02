package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.TutorLegalDTO;
import com.spring.learnsphere.service.TutorLegalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los tutores legales en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta y actualización de datos de tutores legales.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutores-legales")
public class TutorLegalController {

    /** Servicio que contiene la lógica de negocio de tutores legales. */
    private final TutorLegalService tutorLegalService;

    /**
     * Obtiene la lista de todos los tutores legales del sistema.
     *
     * @return lista de DTOs con la información de todos los tutores legales
     */
    @GetMapping("/listar")
    public List<TutorLegalDTO> listar() {
        return tutorLegalService.getAll();
    }

    /**
     * Busca un tutor legal por su identificador único.
     *
     * @param id identificador del tutor legal
     * @return DTO con la información del tutor legal encontrado
     */
    @GetMapping("/buscar/{id}")
    public TutorLegalDTO buscarPorId(@PathVariable Integer id) {
        return tutorLegalService.findById(id);
    }

    /**
     * Actualiza los datos de un tutor legal existente.
     *
     * @param id  identificador del tutor legal a actualizar
     * @param dto datos actualizados del tutor legal
     * @return DTO con la información del tutor legal actualizado
     */
    @PutMapping("/editar/{id}")
    public TutorLegalDTO editar(@PathVariable Integer id, @RequestBody TutorLegalDTO dto) {
        return tutorLegalService.update(id, dto);
    }

}
