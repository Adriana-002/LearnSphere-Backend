package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CursoMaterialId implements Serializable {
    private static final long serialVersionUID = 3308977156672251923L;
    @Column(name = "curso_id", nullable = false)
    private Integer cursoId;

    @Column(name = "material_id", nullable = false)
    private Integer materialId;

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CursoMaterialId entity = (CursoMaterialId) o;
        return Objects.equals(this.cursoId, entity.cursoId) &&
                Objects.equals(this.materialId, entity.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursoId, materialId);
    }

}