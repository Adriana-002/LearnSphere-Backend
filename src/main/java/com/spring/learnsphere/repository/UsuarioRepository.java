package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}