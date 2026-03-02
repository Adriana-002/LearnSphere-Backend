package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un curso en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "cursos" de la base de datos y contiene
 * la información de los cursos académicos disponibles en el centro educativo.
 *
 * @author Adriana
 */
@Entity
@Table(name = "cursos")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Curso {
    /**
     * Identificador único del curso.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id", nullable = false)
    private Integer id;

    /**
     * Nombre del curso.
     * Campo obligatorio con una longitud máxima de 100 caracteres.
     * Ejemplo: 1º Bachillerato A, 3º ESO B, etc.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Año académico al que pertenece el curso.
     * Campo obligatorio con una longitud máxima de 9 caracteres.
     * Formato utilizado: YYYY-YYYY (ejemplo: 2024-2025).
     */
    @Column(name = "\"año_academico\"", nullable = false, length = 9)
    private String añoAcademico;

}