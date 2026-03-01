package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.TutorAlumno;
import com.spring.learnsphere.model.TutorAlumnoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorAlumnoRepository extends JpaRepository<TutorAlumno, TutorAlumnoId> {
    List<TutorAlumno> findAllByTutor_Id(Integer tutorId);

    TutorAlumno findAllByAlumnoId(Integer alumnoId);
}