package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio que proporciona acceso a los datos de los cursos en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Curso,
 * incluyendo la búsqueda por identificador.
 *
 * @author Adriana
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    /**
     * Busca un curso por su identificador único.
     *
     * @param id identificador del curso
     * @return Optional con el curso encontrado, o vacío si no existe
     */
    Optional<Curso> findById(Integer id);
}