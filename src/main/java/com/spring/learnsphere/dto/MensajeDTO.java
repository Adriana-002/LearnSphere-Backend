package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un mensaje.
 *
 * Este DTO se utiliza para transmitir información de mensajes entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MensajeDTO {
    /** Identificador único del mensaje. */
    private Integer mensajeId;

    /** ID del chat al que pertenece el mensaje. */
    private Integer chatId;

    /** ID del usuario que envía el mensaje. */
    private Integer userId;

    /** Contenido del mensaje. */
    private String texto;

    /** Fecha y hora de envío del mensaje. */
    private String fechaEnvio;

    /** Nombre del usuario remitente. */
    private String nombreRemitente;
}
