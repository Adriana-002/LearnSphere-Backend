package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.UsuarioChat;
import com.spring.learnsphere.model.UsuarioChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioChatRepository extends JpaRepository<UsuarioChat, UsuarioChatId> {
    List<UsuarioChat> findByUserId(Integer userId);
    List<UsuarioChat> findByChatId(Integer chatId);
    Optional<UsuarioChat> findByChatIdAndUserIdNot(Integer chatId, Integer userId);
}