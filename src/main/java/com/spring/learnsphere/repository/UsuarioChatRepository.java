package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.UsuarioChat;
import com.spring.learnsphere.model.UsuarioChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio que proporciona acceso a los datos de la relación usuario-chat en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad UsuarioChat,
 * utilizando una clave primaria compuesta (UsuarioChatId).
 *
 * @author Adriana
 */
@Repository
public interface UsuarioChatRepository extends JpaRepository<UsuarioChat, UsuarioChatId> {
    /**
     * Obtiene todas las relaciones usuario-chat de un usuario específico.
     *
     * @param userId identificador del usuario
     * @return lista de relaciones usuario-chat del usuario indicado
     */
    List<UsuarioChat> findByUserId(Integer userId);

    /**
     * Obtiene todas las relaciones usuario-chat de un chat específico.
     *
     * @param chatId identificador del chat
     * @return lista de relaciones usuario-chat del chat indicado
     */
    List<UsuarioChat> findByChatId(Integer chatId);

    /**
     * Obtiene el otro participante de un chat excluyendo al usuario indicado.
     *
     * @param chatId identificador del chat
     * @param userId identificador del usuario a excluir
     * @return Optional con la relación usuario-chat del otro participante, o vacío si no existe
     */
    Optional<UsuarioChat> findByChatIdAndUserIdNot(Integer chatId, Integer userId);
}