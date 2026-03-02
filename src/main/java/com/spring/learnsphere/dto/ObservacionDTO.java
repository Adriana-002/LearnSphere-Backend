package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de una observación.
 *
 * Este DTO se utiliza para transmitir información de observaciones entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObservacionDTO {
    /** Identificador único de la observación. */
    private Integer observacionId;

    /** ID del alumno sobre el cual se realiza la observación. */
    private Integer alumnoId;

    /** ID de la asignatura relacionada. */
    private Integer asignaturaId;

    /** Trimestre de la observación. */
    private Integer trimestre;

    /** Contenido de la observación. */
    private String mensaje;

    /** ID del profesor que realizó la observación. */
    private Integer profesorId;

    /** Fecha de la observación. */
    private String fecha;

    /** Nombre del profesor que realizó la observación. */
    private String profesorNombre;

    /** Nombre de la asignatura relacionada. */
    private String asignaturaNombre;
}
