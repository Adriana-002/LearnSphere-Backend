package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa la relación entre un profesor y una asignatura en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "profesor_asignatura" de la base de datos y establece
 * la relación muchos-a-muchos entre profesores y asignaturas, indicando qué asignaturas
 * imparte cada profesor.
 *
 * Utiliza una clave primaria compuesta (ProfesorAsignaturaId) que combina los IDs del profesor y la asignatura.
 *
 * @author Adriana
 */
@Entity
@Table(name = "profesor_asignatura")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ProfesorAsignatura {
    /**
     * Clave primaria compuesta de la entidad.
     * Combina el ID del profesor y el ID de la asignatura.
     */
    @EmbeddedId
    private ProfesorAsignaturaId id;

    /**
     * Profesor que forma parte de la relación.
     * Relación ManyToOne con la entidad Profesor.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("profesorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    /**
     * Asignatura que forma parte de la relación.
     * Relación ManyToOne con la entidad Asignatura.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("asignaturaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

}