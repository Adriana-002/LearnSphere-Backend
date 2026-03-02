package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de la relación alumno-curso.
 *
 * Este DTO se utiliza para transmitir información de la relación entre
 * alumnos y cursos en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnoCursoDTO {
    /** ID del alumno. */
    private Integer alumnoId;

    /** ID del curso. */
    private Integer cursoId;
}
