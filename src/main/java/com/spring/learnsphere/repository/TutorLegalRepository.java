package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.TutorLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorLegalRepository extends JpaRepository<TutorLegal, Integer> {
}