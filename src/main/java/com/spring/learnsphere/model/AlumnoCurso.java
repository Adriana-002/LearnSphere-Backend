package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa la relación entre un alumno y un curso en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "alumno_curso" de la base de datos y establece
 * la relación muchos-a-muchos entre alumnos y cursos, indicando en cuál o cuáles
 * cursos está matriculado cada alumno.
 *
 * Utiliza una clave primaria compuesta (AlumnoCursoId) que combina los IDs del alumno y el curso.
 *
 * @author Adriana
 */
@Entity
@Table(name = "alumno_curso")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class AlumnoCurso {
    /**
     * Clave primaria compuesta de la entidad.
     * Combina el ID del alumno y el ID del curso.
     */
    @EmbeddedId
    private AlumnoCursoId id;

    /**
     * Alumno que forma parte de la relación.
     * Relación ManyToOne con la entidad Alumno.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("alumnoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    /**
     * Curso que forma parte de la relación.
     * Relación ManyToOne con la entidad Curso.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("cursoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

}