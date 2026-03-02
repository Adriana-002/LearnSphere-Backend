package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.CursoMaterial;
import com.spring.learnsphere.model.CursoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que proporciona acceso a los datos de la relación curso-material en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad CursoMaterial,
 * utilizando una clave primaria compuesta (CursoMaterialId).
 *
 * @author Adriana
 */
@Repository
public interface CursoMaterialRepository extends JpaRepository<CursoMaterial, CursoMaterialId> {
    /**
     * Obtiene todas las relaciones curso-material de un curso específico.
     *
     * @param cursoId identificador del curso
     * @return lista de relaciones curso-material del curso indicado
     */
    List<CursoMaterial> findByCursoId(Integer cursoId);

    /**
     * Obtiene todas las relaciones curso-material de una lista de cursos.
     *
     * @param cursoIds lista de identificadores de cursos
     * @return lista de relaciones curso-material de los cursos indicados
     */
    List<CursoMaterial> findByCursoIdIn(List<Integer> cursoIds);

    /**
     * Obtiene todas las relaciones curso-material de un material específico.
     *
     * @param materialId identificador del material escolar
     * @return lista de relaciones curso-material del material indicado
     */
    List<CursoMaterial> findByMaterialId(Integer materialId);
}