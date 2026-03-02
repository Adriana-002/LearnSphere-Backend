package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de las faltas de asistencia en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Falta,
 * incluyendo la búsqueda por alumno.
 *
 * @author Adriana
 */
@Repository
public interface FaltaRepository extends JpaRepository<Falta, Integer> {
    /**
     * Obtiene todas las faltas de un alumno específico.
     *
     * @param alumnoId identificador del alumno
     * @return lista de faltas del alumno indicado
     */
    List<Falta> findByAlumnoId(Integer alumnoId);

}