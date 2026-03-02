package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa una nota o calificación de un alumno en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "notas" de la base de datos y contiene
 * las calificaciones de los alumnos por asignatura y trimestre.
 *
 * @author Adriana
 */
@Entity
@Table(name = "notas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Nota {
    /**
     * Identificador único de la nota.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nota_id", nullable = false)
    private Integer id;

    /**
     * Alumno que posee la nota.
     * Relación ManyToOne con la entidad Alumno.
     * La eliminación en cascada garantiza que al eliminar un alumno se eliminen sus notas.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    /**
     * Asignatura y curso asociados a la nota.
     * Relación ManyToOne con la entidad CursoAsignatura.
     * La eliminación en cascada garantiza que al eliminar una asignatura de curso se eliminen las notas.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "curso_asignatura_id", nullable = false)
    private CursoAsignatura cursoAsignatura;

    /**
     * Trimestre en el que se registra la calificación.
     * Campo obligatorio con valores típicos: 1, 2 o 3.
     */
    @Column(name = "trimestre", nullable = false)
    private Integer trimestre;

    /**
     * Calificación numérica del alumno.
     * Campo opcional que almacena un valor decimal con precisión de 4 dígitos y 2 decimales.
     * Ejemplo: 8.50 para una calificación de 8.5/10.
     */
    @Column(name = "calificacion", precision = 4, scale = 2)
    private BigDecimal calificacion;

    /**
     * Fecha de registro de la nota en el sistema.
     * Se establece automáticamente a la fecha actual al crear la nota.
     */
    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

}