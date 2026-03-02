package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un alumno.
 *
 * Este DTO se utiliza para transmitir información de alumnos entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnoDTO {
    /** Identificador único del alumno. */
    @JsonProperty("alumno_id")
    private Integer alumnoId;

    /** Nombre del alumno. */
    @JsonProperty("nombre")
    private String nombre;

    /** Apellidos del alumno. */
    @JsonProperty("apellidos")
    private String apellidos;

    /** Fecha de nacimiento del alumno. */
    private String fechaNacimiento;

    /** URL de la foto de perfil del alumno. */
    private String fotoUrl;

    /** ID del curso al que pertenece el alumno. */
    @JsonProperty("cursoId")
    private Integer cursoId;
}
