package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.ProfesorAsignatura;
import com.spring.learnsphere.model.ProfesorAsignaturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de la relación profesor-asignatura en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad ProfesorAsignatura,
 * utilizando una clave primaria compuesta (ProfesorAsignaturaId).
 *
 * @author Adriana
 */
@Repository
public interface ProfesorAsignaturaRepository extends JpaRepository<ProfesorAsignatura, ProfesorAsignaturaId> {
    /**
     * Obtiene todas las relaciones profesor-asignatura de un profesor específico.
     *
     * @param profesorId identificador del profesor
     * @return lista de relaciones profesor-asignatura del profesor indicado
     */
    List<ProfesorAsignatura> findByProfesor_Id(Integer profesorId);
}