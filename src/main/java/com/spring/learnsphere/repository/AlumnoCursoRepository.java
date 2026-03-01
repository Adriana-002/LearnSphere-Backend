package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.AlumnoCurso;
import com.spring.learnsphere.model.AlumnoCursoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoCursoRepository extends JpaRepository<AlumnoCurso, AlumnoCursoId> {
    Optional<AlumnoCurso> findByAlumnoId(Integer alumnoId);
    List<AlumnoCurso> findByCursoId(Integer cursoId);
    List<AlumnoCurso> findByCursoIdIn(List<Integer> cursoIds);
}