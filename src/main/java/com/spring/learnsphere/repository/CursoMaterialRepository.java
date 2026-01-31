package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.CursoMaterial;
import com.spring.learnsphere.model.CursoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoMaterialRepository extends JpaRepository<CursoMaterial, CursoMaterialId> {
}