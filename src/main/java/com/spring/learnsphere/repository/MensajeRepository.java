package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio que proporciona acceso a los datos de los mensajes en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Mensaje,
 * incluyendo consultas por chat con distintas ordenaciones.
 *
 * @author Adriana
 */
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    /**
     * Obtiene todos los mensajes de un chat específico.
     *
     * @param chatId identificador del chat
     * @return lista de mensajes del chat indicado
     */
    List<Mensaje> findByChat_Id(Integer chatId);

    /**
     * Obtiene todos los mensajes de un chat ordenados por fecha de envío ascendente.
     *
     * @param chatId identificador del chat
     * @return lista de mensajes del chat ordenados del más antiguo al más reciente
     */
    List<Mensaje> findByChat_IdOrderByFechaEnvioAsc(Integer chatId);

    /**
     * Obtiene el último mensaje enviado en un chat.
     *
     * @param chatId identificador del chat
     * @return Optional con el mensaje más reciente del chat, o vacío si no hay mensajes
     */
    Optional<Mensaje> findTopByChat_IdOrderByFechaEnvioDesc(Integer chatId);
}