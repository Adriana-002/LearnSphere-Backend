package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.ProfesorDTO;
import com.spring.learnsphere.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los profesores en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta y actualización de datos de profesores.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profesores")
public class ProfesorController {

    /** Servicio que contiene la lógica de negocio de profesores. */
    private final ProfesorService profesorService;

    /**
     * Obtiene la lista de todos los profesores del sistema.
     *
     * @return lista de DTOs con la información de todos los profesores
     */
    @GetMapping("/listar")
    public List<ProfesorDTO> listar() {
        return profesorService.getAll();
    }

    /**
     * Busca un profesor por su identificador único.
     *
     * @param id identificador del profesor
     * @return DTO con la información del profesor encontrado
     */
    @GetMapping("/buscar/{id}")
    public ProfesorDTO buscarPorId(@PathVariable Integer id) {
        return profesorService.findById(id);
    }

    /**
     * Actualiza los datos de un profesor existente.
     *
     * @param id  identificador del profesor a actualizar
     * @param dto datos actualizados del profesor
     * @return DTO con la información del profesor actualizado
     */
    @PutMapping("/editar/{id}")
    public ProfesorDTO editar(@PathVariable Integer id, @RequestBody ProfesorDTO dto) {
        return profesorService.update(id, dto);
    }

}
