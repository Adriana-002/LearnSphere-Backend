package com.spring.learnsphere.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "curso_material")
public class CursoMaterial {
    @EmbeddedId
    private CursoMaterialId id;

    @MapsId("cursoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @MapsId("materialId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "material_id", nullable = false)
    private MaterialEscolar material;

    public CursoMaterialId getId() {
        return id;
    }

    public void setId(CursoMaterialId id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public MaterialEscolar getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEscolar material) {
        this.material = material;
    }

}