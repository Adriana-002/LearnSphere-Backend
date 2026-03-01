package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByAlumno_IdAndTrimestre(Integer alumnoId, Integer trimestre);
    List<Nota> findByAlumno_Id(Integer alumnoId);
}