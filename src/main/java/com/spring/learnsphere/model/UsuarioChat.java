package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa la relación entre un usuario y un chat en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "usuario_chat" de la base de datos y establece
 * la relación muchos-a-muchos entre usuarios y chats, indicando qué usuarios participan
 * en cada conversación de chat.
 *
 * Utiliza una clave primaria compuesta (UsuarioChatId) que combina los IDs del usuario y el chat.
 *
 * @author Adriana
 */
@Entity
@Table(name = "usuario_chat")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class UsuarioChat {
    /**
     * Clave primaria compuesta de la entidad.
     * Combina el ID del usuario y el ID del chat.
     */
    @EmbeddedId
    private UsuarioChatId id;

    /**
     * Usuario que forma parte de la relación.
     * Relación ManyToOne con la entidad Usuario.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    /**
     * Chat que forma parte de la relación.
     * Relación ManyToOne con la entidad Chat.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("chatId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

}