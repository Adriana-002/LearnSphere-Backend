package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un tutor legal.
 *
 * Este DTO se utiliza para transmitir información de tutores legales entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TutorLegalDTO {
    /** Identificador único del usuario tutor. */
    private Integer userId;

    /** Nombre del tutor legal. */
    private String nombre;

    /** Apellidos del tutor legal. */
    private String apellidos;

    /** Correo electrónico del tutor legal. */
    private String email;

    /** Número de teléfono del tutor legal. */
    private String telefono;

    /** Indica si el tutor es también alumno del sistema. */
    private Boolean esAlumno;
}
