package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.CursoAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoAsignaturaRepository extends JpaRepository<CursoAsignatura, Integer> {
}