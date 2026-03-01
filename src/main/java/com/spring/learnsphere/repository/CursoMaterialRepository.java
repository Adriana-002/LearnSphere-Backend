package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.CursoMaterial;
import com.spring.learnsphere.model.CursoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoMaterialRepository extends JpaRepository<CursoMaterial, CursoMaterialId> {
    List<CursoMaterial> findByCursoId(Integer cursoId);
    List<CursoMaterial> findByCursoIdIn(List<Integer> cursoIds);
}