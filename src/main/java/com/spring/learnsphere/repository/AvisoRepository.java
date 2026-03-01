package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Integer> {
    List<Aviso> findByEsImportanteTrue();
    List<Aviso> findAllByOrderByFechaPublicacionDesc();
}