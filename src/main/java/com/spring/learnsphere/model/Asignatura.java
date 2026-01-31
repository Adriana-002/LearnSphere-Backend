package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asignaturas")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asignatura_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

}