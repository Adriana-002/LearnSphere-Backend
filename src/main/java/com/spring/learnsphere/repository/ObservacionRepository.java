package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de las observaciones en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Observacion,
 * incluyendo la búsqueda por alumno.
 *
 * @author Adriana
 */
@Repository
public interface ObservacionRepository extends JpaRepository<Observacion, Integer> {
    /**
     * Obtiene todas las observaciones de un alumno específico.
     *
     * @param alumnoId identificador del alumno
     * @return lista de observaciones del alumno indicado
     */
    List<Observacion> findByAlumnoId(Integer alumnoId);
}