package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.UsuarioChat;
import com.spring.learnsphere.model.UsuarioChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioChatRepository extends JpaRepository<UsuarioChat, UsuarioChatId> {
}