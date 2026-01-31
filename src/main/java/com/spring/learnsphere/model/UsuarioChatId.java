package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioChatId implements Serializable {
    private static final long serialVersionUID = 3433453511712917263L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "chat_id", nullable = false)
    private Integer chatId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioChatId entity = (UsuarioChatId) o;
        return Objects.equals(this.chatId, entity.chatId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, userId);
    }

}