package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUser_IdOrderByFechaDesc(Integer userId);
}