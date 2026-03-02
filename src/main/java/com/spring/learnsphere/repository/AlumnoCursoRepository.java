package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.AlumnoCurso;
import com.spring.learnsphere.model.AlumnoCursoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio que proporciona acceso a los datos de la relación alumno-curso en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad AlumnoCurso,
 * utilizando una clave primaria compuesta (AlumnoCursoId).
 *
 * @author Adriana
 */
@Repository
public interface AlumnoCursoRepository extends JpaRepository<AlumnoCurso, AlumnoCursoId> {
    /**
     * Busca la relación alumno-curso por el identificador del alumno.
     *
     * @param alumnoId identificador del alumno
     * @return Optional con la relación encontrada, o vacío si no existe
     */
    Optional<AlumnoCurso> findByAlumnoId(Integer alumnoId);

    /**
     * Obtiene todas las relaciones alumno-curso de un curso específico.
     *
     * @param cursoId identificador del curso
     * @return lista de relaciones alumno-curso del curso indicado
     */
    List<AlumnoCurso> findByCursoId(Integer cursoId);

    /**
     * Obtiene todas las relaciones alumno-curso de una lista de cursos.
     *
     * @param cursoIds lista de identificadores de cursos
     * @return lista de relaciones alumno-curso de los cursos indicados
     */
    List<AlumnoCurso> findByCursoIdIn(List<Integer> cursoIds);
}