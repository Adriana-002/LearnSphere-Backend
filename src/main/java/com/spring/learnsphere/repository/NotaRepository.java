package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de las notas en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Nota,
 * incluyendo consultas por alumno y por trimestre.
 *
 * @author Adriana
 */
@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
    /**
     * Obtiene las notas de un alumno en un trimestre específico.
     *
     * @param alumnoId  identificador del alumno
     * @param trimestre número del trimestre
     * @return lista de notas del alumno en el trimestre indicado
     */
    List<Nota> findByAlumno_IdAndTrimestre(Integer alumnoId, Integer trimestre);

    /**
     * Obtiene todas las notas de un alumno.
     *
     * @param alumnoId identificador del alumno
     * @return lista de todas las notas del alumno
     */
    List<Nota> findByAlumno_Id(Integer alumnoId);
}