package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de la relación profesor-asignatura.
 *
 * Este DTO se utiliza para transmitir información de la relación entre
 * profesores y asignaturas en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfesorAsignaturaDTO {
    /** ID del profesor. */
    private Integer profesorId;

    /** ID de la asignatura. */
    private Integer asignaturaId;
}
