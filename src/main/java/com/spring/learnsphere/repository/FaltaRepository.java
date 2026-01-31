package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Integer> {
}