package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa la relación entre un tutor legal y un alumno en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "tutor_alumno" de la base de datos y establece
 * la relación muchos-a-muchos entre tutores legales y alumnos, indicando qué tutores
 * son responsables de cada alumno, incluyendo el tipo de parentesco.
 *
 * Utiliza una clave primaria compuesta (TutorAlumnoId) que combina los IDs del tutor y el alumno.
 *
 * @author Adriana
 */
@Entity
@Table(name = "tutor_alumno")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TutorAlumno {
    /**
     * Clave primaria compuesta de la entidad.
     * Combina el ID del tutor y el ID del alumno.
     */
    @EmbeddedId
    private TutorAlumnoId id;

    /**
     * Tutor legal que forma parte de la relación.
     * Relación ManyToOne con la entidad TutorLegal.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("tutorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tutor_id", nullable = false)
    private TutorLegal tutor;

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
     * Parentesco entre el tutor y el alumno.
     * Campo opcional con una longitud máxima de 50 caracteres.
     * Ejemplos: Padre, Madre, Abuelo/a, Tutor legal, etc.
     */
    @Column(name = "parentesco", length = 50)
    private String parentesco;

}