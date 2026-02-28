package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<Usuario> findByRol(String rol);
    List<Usuario> finByNombe(String nombre, String apellidos);
    List<Usuario> findListByRol(String rol);


}