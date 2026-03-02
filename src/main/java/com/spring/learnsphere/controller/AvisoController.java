package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AvisoDTO;
import com.spring.learnsphere.service.AvisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los avisos en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación y eliminación de avisos,
 * incluyendo el filtrado de avisos importantes.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/avisos")
public class AvisoController {

    /** Servicio que contiene la lógica de negocio de avisos. */
    private final AvisoService avisoService;

    /**
     * Obtiene la lista de todos los avisos ordenados por fecha de publicación.
     *
     * @return lista de DTOs con la información de todos los avisos
     */
    @GetMapping("/listar")
    public List<AvisoDTO> getAllAvisos() {
        return avisoService.getAllAvisos();
    }

    /**
     * Obtiene la lista de avisos marcados como importantes.
     *
     * @return lista de DTOs con la información de los avisos importantes
     */
    @GetMapping("/importantes")
    public List<AvisoDTO> getImportantes() {
        return avisoService.getImportantes();
    }

    /**
     * Crea un nuevo aviso en el sistema.
     *
     * @param dto datos del aviso a crear
     * @return DTO con la información del aviso creado
     */
    @PostMapping("/crear")
    public AvisoDTO createAviso(@RequestBody AvisoDTO dto) {
        return avisoService.createAviso(dto);
    }

    /**
     * Elimina un aviso del sistema.
     *
     * @param id identificador del aviso a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public void deleteAviso(@PathVariable Integer id) {
        avisoService.deleteAviso(id);
    }

}
