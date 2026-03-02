package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

/**
 * Entidad que representa una observación o anotación sobre un alumno en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "observaciones" de la base de datos y contiene
 * observaciones académicas, conductuales o de rendimiento realizadas por profesores sobre alumnos.
 *
 * @author Adriana
 */
@Entity
@Table(name = "observaciones")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Observacion {
    /**
     * Identificador único de la observación.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "observacion_id", nullable = false)
    private Integer id;

    /**
     * Alumno sobre el cual se realiza la observación.
     * Relación ManyToOne con la entidad Alumno.
     * Campo obligatorio y la eliminación en cascada garantiza que al eliminar un alumno
     * se eliminen todas sus observaciones.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    /**
     * Asignatura relacionada con la observación.
     * Relación ManyToOne con la entidad Asignatura.
     * Campo opcional que se puede poner a null si la observación no está asociada a una asignatura específica.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    /**
     * Trimestre en el que se realizó la observación.
     * Campo opcional con valores típicos: 1, 2 o 3.
     */
    @Column(name = "trimestre")
    private Integer trimestre;

    /**
     * Texto de la observación.
     * Campo obligatorio que contiene el mensaje o comentario detallado sobre el alumno.
     * Sin límite de longitud (Integer.MAX_VALUE).
     */
    @Column(name = "mensaje", nullable = false, length = Integer.MAX_VALUE)
    private String mensaje;

    /**
     * Profesor que realizó la observación.
     * Relación ManyToOne con la entidad Profesor.
     * Campo opcional que se puede poner a null.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    /**
     * Fecha de creación de la observación.
     * Se establece automáticamente a la fecha y hora actual al crear la observación.
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha")
    private Instant fecha;

}