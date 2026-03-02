package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidad que representa una falta de asistencia de un alumno en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "faltas" de la base de datos y contiene
 * el registro de ausencias y retrasos de los alumnos, incluyendo la fecha y hora.
 *
 * @author Adriana
 */
@Entity
@Table(name = "faltas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Falta {
    /**
     * Identificador único de la falta.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "falta_id", nullable = false)
    private Integer id;

    /**
     * Alumno que tiene la falta de asistencia.
     * Relación ManyToOne con la entidad Alumno.
     * La eliminación en cascada garantiza que al eliminar un alumno se eliminen sus faltas.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    /**
     * Fecha en la que ocurrió la falta de asistencia.
     * Campo obligatorio que se establece automáticamente a la fecha actual al crear la falta.
     */
    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    /**
     * Hora en la que se registró la falta.
     * Campo opcional que permite registrar la hora específica de la ausencia o retraso.
     */
    @Column(name = "hora")
    private LocalTime hora;

}