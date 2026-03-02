package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un material escolar.
 *
 * Este DTO se utiliza para transmitir información de materiales escolares entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialEscolarDTO {
    /** Identificador único del material escolar. */
    private Integer materialId;

    /** Nombre del material. */
    private String nombre;

    /** Editorial del material. */
    private String editorial;

    /** ISBN del material. */
    private String isbn;

    /** ID del curso asociado. */
    private Integer cursoId;
}
