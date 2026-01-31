package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "notas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nota_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "curso_asignatura_id", nullable = false)
    private CursoAsignatura cursoAsignatura;

    @Column(name = "trimestre", nullable = false)
    private Integer trimestre;

    @Column(name = "calificacion", precision = 4, scale = 2)
    private BigDecimal calificacion;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

}