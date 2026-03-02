package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.TutorLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que proporciona acceso a los datos de los tutores legales en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad TutorLegal.
 *
 * @author Adriana
 */
@Repository
public interface TutorLegalRepository extends JpaRepository<TutorLegal, Integer> {
}