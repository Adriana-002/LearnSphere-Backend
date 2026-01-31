package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "faltas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Falta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "falta_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

}