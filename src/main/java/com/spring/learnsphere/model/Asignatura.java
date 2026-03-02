package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa una asignatura en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "asignaturas" de la base de datos y contiene
 * la información de las asignaturas o materias que se imparten en el centro educativo.
 *
 * @author Adriana
 */
@Entity
@Table(name = "asignaturas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Asignatura {
    /**
     * Identificador único de la asignatura.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asignatura_id", nullable = false)
    private Integer id;

    /**
     * Nombre de la asignatura.
     * Campo obligatorio con una longitud máxima de 150 caracteres.
     * Ejemplo: Matemáticas, Lengua, Ciencias Naturales, etc.
     */
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

}