package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que proporciona acceso a los datos de los alumnos en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Alumno.
 *
 * @author Adriana
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}