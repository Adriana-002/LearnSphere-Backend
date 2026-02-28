package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByAlumnoId(Integer alumnoId);
    List<Nota> findByCursoAsignaturaId(Integer cursoAsignaturaId);

    //Este metodo es para verificaciones
    boolean existsByAlumnoIdAndCursoAsignaturaIdAndTrimestre(Integer alumnoId, Integer cursoAsignaturaId, Integer trimestre);
}