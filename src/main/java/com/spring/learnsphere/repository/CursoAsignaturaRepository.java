package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.CursoAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoAsignaturaRepository extends JpaRepository<CursoAsignatura, Integer> {
    List<CursoAsignatura> findByCursoId(Integer cursoId);
    List<CursoAsignatura> findByAsignaturaId(Integer asignaturaId);
}