package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de una falta de asistencia.
 *
 * Este DTO se utiliza para transmitir información de faltas entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaltaDTO {
    /** Identificador único de la falta. */
    private Integer faltaId;

    /** ID del alumno que tiene la falta. */
    private Integer alumnoId;

    /** Fecha de la falta de asistencia. */
    private String fecha;

    /** Hora de la falta. */
    private String hora;
}
