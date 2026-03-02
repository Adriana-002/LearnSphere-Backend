package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de la relación curso-asignatura.
 *
 * Este DTO se utiliza para transmitir información de la relación entre
 * cursos y asignaturas en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoAsignaturaDTO {
    /** ID de la relación curso-asignatura. */
    private Integer cursoAsignaturaId;

    /** ID del curso. */
    private Integer cursoId;

    /** ID de la asignatura. */
    private Integer asignaturaId;
}
