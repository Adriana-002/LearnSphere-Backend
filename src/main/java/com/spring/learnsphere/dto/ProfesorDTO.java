package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un profesor.
 *
 * Este DTO se utiliza para transmitir información de profesores entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfesorDTO {
    /** Identificador único del usuario profesor. */
    private Integer userId;

    /** Nombre del profesor. */
    private String nombre;

    /** Apellidos del profesor. */
    private String apellidos;

    /** Correo electrónico del profesor. */
    private String email;

    /** Departamento académico del profesor. */
    private String departamento;
}
