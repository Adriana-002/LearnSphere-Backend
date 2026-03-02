package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un aviso.
 *
 * Este DTO se utiliza para transmitir información de avisos entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvisoDTO {
    /** Identificador único del aviso. */
    private Integer avisoId;

    /** Título del aviso. */
    private String titulo;

    /** Contenido del aviso. */
    private String mensaje;

    /** Indica si el aviso es importante. */
    private Boolean esImportante;

    /** Fecha de publicación del aviso. */
    private String fechaPublicacion;
}
