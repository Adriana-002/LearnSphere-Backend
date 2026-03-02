package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de una asignatura.
 *
 * Este DTO se utiliza para transmitir información de asignaturas entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsignaturaDTO {
    /** Identificador único de la asignatura. */
    private Integer asignaturaId;

    /** Nombre de la asignatura. */
    private String nombre;
}
