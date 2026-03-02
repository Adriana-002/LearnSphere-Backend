package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entidad que representa a un alumno en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "alumnos" de la base de datos y contiene
 * la información personal básica de cada estudiante registrado en la plataforma.
 *
 * @author Adriana
 */
@Entity
@Table(name = "alumnos")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Alumno {

    /**
     * Identificador único del alumno.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alumno_id", nullable = false)
    private Integer id;

    /**
     * Nombre del alumno.
     * Campo obligatorio con una longitud máxima de 100 caracteres.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Apellidos del alumno.
     * Campo obligatorio con una longitud máxima de 200 caracteres.
     */
    @Column(name = "apellidos", nullable = false, length = 200)
    private String apellidos;

    /**
     * Fecha de nacimiento del alumno.
     * Campo obligatorio de tipo LocalDate.
     */
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    /**
     * URL de la foto de perfil del alumno.
     * Campo opcional con una longitud máxima de 500 caracteres.
     */
    @Column(name = "foto_url", length = 500)
    private String fotoUrl;
}