package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que proporciona acceso a los datos de los chats en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Chat.
 *
 * @author Adriana
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}