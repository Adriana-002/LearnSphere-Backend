package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    Optional<Curso> findById(Integer id);
}