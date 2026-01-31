package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.TutorAlumno;
import com.spring.learnsphere.model.TutorAlumnoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorAlumnoRepository extends JpaRepository<TutorAlumno, TutorAlumnoId> {
}