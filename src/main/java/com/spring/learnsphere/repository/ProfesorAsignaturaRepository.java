package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.ProfesorAsignatura;
import com.spring.learnsphere.model.ProfesorAsignaturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorAsignaturaRepository extends JpaRepository<ProfesorAsignatura, ProfesorAsignaturaId> {
}