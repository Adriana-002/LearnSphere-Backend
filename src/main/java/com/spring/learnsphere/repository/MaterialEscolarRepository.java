package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.MaterialEscolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialEscolarRepository extends JpaRepository<MaterialEscolar, Integer> {
}