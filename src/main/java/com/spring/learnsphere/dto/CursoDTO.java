package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un curso.
 *
 * Este DTO se utiliza para transmitir información de cursos entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoDTO {
    /** Identificador único del curso. */
    private Integer cursoId;

    /** Nombre del curso. */
    private String nombre;

    /** Año académico del curso. */
    private String añoAcademico;
}
