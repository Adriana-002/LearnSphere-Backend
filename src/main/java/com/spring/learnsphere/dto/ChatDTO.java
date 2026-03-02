package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO que representa la transferencia de datos de un chat.
 *
 * Este DTO se utiliza para transmitir información de chats entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatDTO {
    /** Identificador único del chat. */
    private Integer chatId;

    /** Tipo de chat (individual o grupo). */
    private String tipo;

    /** Nombre del chat. */
    private String nombreChat;

    /** Último mensaje del chat. */
    private String ultimoMensaje;

    /** Tiempo del último mensaje. */
    private String tiempoUltimoMensaje;

    /** ID del usuario creador del chat. */
    private Integer creadorId;

    /** Nombre del alumno asociado. */
    private String nombreAlumno;

    /** Nombre de la asignatura asociada. */
    private String nombreAsignatura;

    /** IDs de los usuarios participantes. */
    @JsonAlias("usuarioIds")
    private List<Integer> userIds;
}
