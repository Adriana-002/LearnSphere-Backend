package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.AlumnoCurso;
import com.spring.learnsphere.model.AlumnoCursoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoCursoRepository extends JpaRepository<AlumnoCurso, AlumnoCursoId> {
}