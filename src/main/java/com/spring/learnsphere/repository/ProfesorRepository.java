package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que proporciona acceso a los datos de los profesores en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Profesor.
 *
 * @author Adriana
 */
@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}