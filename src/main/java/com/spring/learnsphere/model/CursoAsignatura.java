package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa la relación entre un curso y una asignatura en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "curso_asignatura" de la base de datos y establece
 * la relación muchos-a-muchos entre cursos y asignaturas, indicando qué asignaturas
 * se imparten en cada curso.
 *
 * @author Adriana
 */
@Entity
@Table(name = "curso_asignatura")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class CursoAsignatura {
    /**
     * Identificador único de la relación entre curso y asignatura.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_asignatura_id", nullable = false)
    private Integer id;

    /**
     * Curso que forma parte de la relación.
     * Relación ManyToOne con la entidad Curso.
     * Campo obligatorio y la eliminación en cascada garantiza la integridad referencial.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    /**
     * Asignatura que forma parte de la relación.
     * Relación ManyToOne con la entidad Asignatura.
     * Campo obligatorio y la eliminación en cascada garantiza la integridad referencial.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

}