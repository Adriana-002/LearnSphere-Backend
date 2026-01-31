package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "material_escolar")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class MaterialEscolar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

}