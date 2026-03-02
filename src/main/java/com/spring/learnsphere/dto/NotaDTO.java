package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de una nota o calificación.
 *
 * Este DTO se utiliza para transmitir información de notas entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDTO {
    /** Identificador único de la nota. */
    private Integer notaId;

    /** ID del alumno que posee la nota. */
    private Integer alumnoId;

    /** ID de la relación curso-asignatura. */
    private Integer cursoAsignaturaId;

    /** Trimestre al que corresponde la nota. */
    private Integer trimestre;

    /** Calificación numérica obtenida. */
    private Double calificacion;

    /** Fecha de registro de la nota. */
    private String fechaRegistro;

    /** Nombre de la asignatura asociada. */
    private String asignaturaNombre;
}
