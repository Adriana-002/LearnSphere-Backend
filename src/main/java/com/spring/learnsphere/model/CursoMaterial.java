package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa la relación entre un curso y un material escolar en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "curso_material" de la base de datos y establece
 * la relación muchos-a-muchos entre cursos y materiales escolares, indicando qué materiales
 * son necesarios para cada curso.
 *
 * Utiliza una clave primaria compuesta (CursoMaterialId) que combina los IDs del curso y el material.
 *
 * @author Adriana
 */
@Entity
@Table(name = "curso_material")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class CursoMaterial {
    /**
     * Clave primaria compuesta de la entidad.
     * Combina el ID del curso y el ID del material.
     */
    @EmbeddedId
    private CursoMaterialId id;

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

    /**
     * Material escolar que forma parte de la relación.
     * Relación ManyToOne con la entidad MaterialEscolar.
     * Se mapea automáticamente desde el ID embebido.
     * La eliminación en cascada garantiza la integridad referencial.
     */
    @MapsId("materialId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "material_id", nullable = false)
    private MaterialEscolar material;

}