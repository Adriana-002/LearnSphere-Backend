package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProfesorAsignaturaId implements Serializable {
    private static final long serialVersionUID = 8706772925523668369L;
    @Column(name = "profesor_id", nullable = false)
    private Integer profesorId;

    @Column(name = "asignatura_id", nullable = false)
    private Integer asignaturaId;

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProfesorAsignaturaId entity = (ProfesorAsignaturaId) o;
        return Objects.equals(this.asignaturaId, entity.asignaturaId) &&
                Objects.equals(this.profesorId, entity.profesorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignaturaId, profesorId);
    }

}