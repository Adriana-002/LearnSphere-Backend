package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.CursoAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de la relación curso-asignatura en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad CursoAsignatura,
 * incluyendo búsquedas por curso y por asignatura.
 *
 * @author Adriana
 */
@Repository
public interface CursoAsignaturaRepository extends JpaRepository<CursoAsignatura, Integer> {
    /**
     * Obtiene todas las relaciones curso-asignatura de un curso específico.
     *
     * @param cursoId identificador del curso
     * @return lista de relaciones curso-asignatura del curso indicado
     */
    List<CursoAsignatura> findByCursoId(Integer cursoId);

    /**
     * Obtiene todas las relaciones curso-asignatura de una asignatura específica.
     *
     * @param asignaturaId identificador de la asignatura
     * @return lista de relaciones curso-asignatura de la asignatura indicada
     */
    List<CursoAsignatura> findByAsignaturaId(Integer asignaturaId);
}