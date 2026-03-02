package com.spring.learnsphere.repository;

import com.spring.learnsphere.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * Repositorio que proporciona acceso a los datos de los usuarios en el sistema LearnSphere.
 *
 * Extiende JpaRepository para ofrecer operaciones CRUD estándar sobre la entidad Usuario,
 * incluyendo la búsqueda por correo electrónico.
 *
 * @author Adriana
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email correo electrónico del usuario
     * @return el usuario encontrado, o null si no existe
     */
    Usuario findByEmail(String email);
}