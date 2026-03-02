package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.TutorAlumno;
import com.spring.learnsphere.model.TutorAlumnoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de la relación tutor-alumno en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad TutorAlumno,
 * utilizando una clave primaria compuesta (TutorAlumnoId).
 *
 * @author Adriana
 */
@Repository
public interface TutorAlumnoRepository extends JpaRepository<TutorAlumno, TutorAlumnoId> {
    /**
     * Obtiene todas las relaciones tutor-alumno de un tutor específico.
     *
     * @param tutorId identificador del tutor legal
     * @return lista de relaciones tutor-alumno del tutor indicado
     */
    List<TutorAlumno> findAllByTutor_Id(Integer tutorId);

    /**
     * Obtiene la relación tutor-alumno de un alumno específico.
     *
     * @param alumnoId identificador del alumno
     * @return la relación tutor-alumno del alumno indicado, o null si no existe
     */
    TutorAlumno findAllByAlumnoId(Integer alumnoId);
}