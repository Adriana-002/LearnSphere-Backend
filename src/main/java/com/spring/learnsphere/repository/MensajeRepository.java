package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByChat_Id(Integer chatId);
    List<Mensaje> findByChat_IdOrderByFechaEnvioAsc(Integer chatId);
    Optional<Mensaje> findTopByChat_IdOrderByFechaEnvioDesc(Integer chatId);
}