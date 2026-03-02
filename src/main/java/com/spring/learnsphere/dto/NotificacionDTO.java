package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de una notificación.
 *
 * Este DTO se utiliza para transmitir información de notificaciones entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificacionDTO {
    /** Identificador único de la notificación. */
    private Integer notificacionId;

    /** ID del usuario destinatario. */
    private Integer userId;

    /** Tipo de notificación. */
    private String tipo;

    /** Contenido de la notificación. */
    private String mensaje;

    /** ID de la entidad relacionada (chat, alumno, material, etc.). */
    private Integer entidadId;

    /** Tipo de entidad relacionada (chat, alumno, material, etc.). */
    private String entidadTipo;

    /** Indica si la notificación ha sido leída. */
    private Boolean leida;

    /** Fecha de la notificación. */
    private String fecha;
}
