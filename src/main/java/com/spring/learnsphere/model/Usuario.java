package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * Entidad que representa a un usuario en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "usuarios" de la base de datos y contiene
 * la información de autenticación y datos básicos de todos los usuarios del sistema,
 * incluyendo alumnos, profesores y tutores legales.
 *
 * @author Adriana
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Usuario {
    /**
     * Identificador único del usuario.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    /**
     * Correo electrónico del usuario.
     * Campo obligatorio y único que se utiliza para la autenticación.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Hash de la contraseña del usuario.
     * Campo obligatorio que almacena la contraseña hasheada por razones de seguridad.
     */
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    /**
     * Nombre del usuario.
     * Campo obligatorio con una longitud máxima de 100 caracteres.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Apellidos del usuario.
     * Campo obligatorio con una longitud máxima de 200 caracteres.
     */
    @Column(name = "apellidos", nullable = false, length = 200)
    private String apellidos;

    /**
     * Número de teléfono del usuario.
     * Campo opcional con una longitud máxima de 20 caracteres.
     */
    @Column(name = "telefono", length = 20)
    private String telefono;

    /**
     * Rol del usuario en el sistema.
     * Campo obligatorio que define el tipo de usuario (ALUMNO, PROFESOR, TUTOR_LEGAL, ADMIN).
     * Máximo 20 caracteres.
     */
    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    /**
     * Fecha de registro del usuario en el sistema.
     * Se establece automáticamente a la fecha y hora actual al crear el usuario.
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_registro")
    private Instant fechaRegistro;

}