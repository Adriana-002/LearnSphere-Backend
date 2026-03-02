package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que proporciona acceso a los datos de las asignaturas en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Asignatura,
 * incluyendo la búsqueda por nombre.
 *
 * @author Adriana
 */
@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    /**
     * Busca una asignatura por su nombre.
     *
     * @param asignaturaNombre nombre de la asignatura
     * @return la asignatura encontrada, o null si no existe
     */
    Asignatura findByNombre(String asignaturaNombre);
}