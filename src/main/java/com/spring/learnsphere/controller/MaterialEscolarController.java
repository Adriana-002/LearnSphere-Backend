package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.MaterialEscolarDTO;
import com.spring.learnsphere.service.MaterialEscolarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las peticiones HTTP relacionadas con los materiales escolares en el sistema LearnSphere.
 *
 * Expone endpoints para la consulta, creación, actualización y eliminación de materiales escolares,
 * incluyendo búsquedas por curso y por profesor.
 *
 * @author Adriana
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/material")
public class MaterialEscolarController {

    /** Servicio que contiene la lógica de negocio de materiales escolares. */
    private final MaterialEscolarService materialService;

    /**
     * Obtiene la lista de todos los materiales escolares del sistema.
     *
     * @return lista de DTOs con la información de todos los materiales
     */
    @GetMapping("/listar")
    public List<MaterialEscolarDTO> getAll() {
        return materialService.getAll();
    }

    /**
     * Obtiene la lista de materiales escolares asociados a un curso.
     *
     * @param cursoId identificador del curso
     * @return lista de DTOs con la información de los materiales del curso
     */
    @GetMapping("/curso/{cursoId}")
    public List<MaterialEscolarDTO> getByCurso(@PathVariable Integer cursoId) {
        return materialService.getByCurso(cursoId);
    }

    /**
     * Obtiene la lista de materiales escolares asociados a los cursos de un profesor.
     *
     * @param profesorId identificador del profesor
     * @return lista de DTOs con la información de los materiales del profesor
     */
    @GetMapping("/profesor/{profesorId}")
    public List<MaterialEscolarDTO> getByProfesor(@PathVariable Integer profesorId) {
        return materialService.getByProfesor(profesorId);
    }

    /**
     * Crea un nuevo material escolar y lo asocia al curso indicado.
     *
     * @param dto datos del material a crear
     * @return DTO con la información del material creado
     */
    @PostMapping("/crear")
    public MaterialEscolarDTO createMaterial(@RequestBody MaterialEscolarDTO dto) {
        return materialService.createMaterial(dto);
    }

    /**
     * Actualiza los datos de un material escolar existente.
     *
     * @param materialId identificador del material a actualizar
     * @param dto        datos actualizados del material
     * @return DTO con la información del material actualizado
     */
    @PutMapping("/editar/{materialId}")
    public MaterialEscolarDTO updateMaterial(@PathVariable Integer materialId, @RequestBody MaterialEscolarDTO dto) {
        return materialService.updateMaterial(materialId, dto);
    }

    /**
     * Elimina un material escolar del sistema.
     *
     * @param materialId identificador del material a eliminar
     */
    @DeleteMapping("/eliminar/{materialId}")
    public void deleteMaterial(@PathVariable Integer materialId) {
        materialService.deleteMaterial(materialId);
    }


}
