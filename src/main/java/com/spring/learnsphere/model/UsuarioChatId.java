package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta de la entidad UsuarioChat.
 *
 * Esta clase mapea la clave primaria compuesta que combina el ID del usuario
 * y el ID del chat. Se utiliza como tipo embebido en la entidad UsuarioChat.
 *
 * @author Adriana
 */
@Embeddable
public class UsuarioChatId implements Serializable {
    private static final long serialVersionUID = 3433453511712917263L;

    /**
     * ID del usuario que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    /**
     * ID del chat que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "chat_id", nullable = false)
    private Integer chatId;

    /**
     * Obtiene el ID del usuario.
     * @return ID del usuario
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Establece el ID del usuario.
     * @param userId ID del usuario a establecer
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el ID del chat.
     * @return ID del chat
     */
    public Integer getChatId() {
        return chatId;
    }

    /**
     * Establece el ID del chat.
     * @param chatId ID del chat a establecer
     */
    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    /**
     * Compara esta instancia con otro objeto para igualdad.
     * @param o objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioChatId entity = (UsuarioChatId) o;
        return Objects.equals(this.chatId, entity.chatId) &&
                Objects.equals(this.userId, entity.userId);
    }

    /**
     * Calcula el hash de esta instancia.
     * @return código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(chatId, userId);
    }

}