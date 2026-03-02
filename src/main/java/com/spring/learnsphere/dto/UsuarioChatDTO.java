package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de la relación usuario-chat.
 *
 * Este DTO se utiliza para transmitir información de la relación entre
 * usuarios y chats en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioChatDTO {
    /** ID del usuario participante. */
    private Integer userId;

    /** ID del chat. */
    private Integer chatId;
}
