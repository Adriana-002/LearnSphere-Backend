package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de la relación tutor-alumno.
 *
 * Este DTO se utiliza para transmitir información de la relación entre
 * tutores legales y alumnos en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TutorAlumnoDTO {
    /** ID del tutor legal. */
    private Integer tutorId;

    /** ID del alumno. */
    private Integer alumnoId;

    /** Tipo de parentesco con el alumno. */
    private String parentesco;
}
