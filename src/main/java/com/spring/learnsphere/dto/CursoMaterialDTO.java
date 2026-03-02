package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de la relación curso-material.
 *
 * Este DTO se utiliza para transmitir información de la relación entre
 * cursos y materiales escolares en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoMaterialDTO {
    /** ID del curso. */
    private Integer cursoId;

    /** ID del material escolar. */
    private Integer materialId;
}
