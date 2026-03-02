package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de los avisos en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Aviso,
 * incluyendo consultas para avisos importantes y ordenación por fecha de publicación.
 *
 * @author Adriana
 */
@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Integer> {
    /**
     * Obtiene todos los avisos marcados como importantes.
     *
     * @return lista de avisos importantes
     */
    List<Aviso> findByEsImportanteTrue();

    /**
     * Obtiene todos los avisos ordenados por fecha de publicación descendente.
     *
     * @return lista de avisos ordenados del más reciente al más antiguo
     */
    List<Aviso> findAllByOrderByFechaPublicacionDesc();
}