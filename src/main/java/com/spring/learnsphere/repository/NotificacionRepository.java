package com.spring.learnsphere.repository;

import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de las notificaciones en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Notificacion,
 * incluyendo consultas por usuario con ordenación y filtrado por tipo.
 *
 * @author Adriana
 */
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    /**
     * Obtiene todas las notificaciones de un usuario ordenadas por fecha descendente.
     *
     * @param userId identificador del usuario
     * @return lista de notificaciones del usuario ordenadas de la más reciente a la más antigua
     */
    List<Notificacion> findByUser_IdOrderByFechaDesc(Integer userId);

    /**
     * Obtiene las notificaciones de un usuario filtradas por tipos específicos y ordenadas por fecha descendente.
     *
     * @param userId identificador del usuario
     * @param tipos  lista de tipos de notificación a incluir
     * @return lista de notificaciones filtradas y ordenadas
     */
    List<Notificacion> findByUser_IdAndTipoInOrderByFechaDesc(Integer userId, List<TipoNotificacion> tipos);
}