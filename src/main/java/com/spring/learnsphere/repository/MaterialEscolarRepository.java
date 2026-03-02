package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.MaterialEscolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que proporciona acceso a los datos de los materiales escolares en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad MaterialEscolar.
 *
 * @author Adriana
 */
@Repository
public interface MaterialEscolarRepository extends JpaRepository<MaterialEscolar, Integer> {
}