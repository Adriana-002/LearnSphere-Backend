package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservacionRepository extends JpaRepository<Observacion, Integer> {
    List<Observacion> findByAlumnoId(Integer alumnoId);
}