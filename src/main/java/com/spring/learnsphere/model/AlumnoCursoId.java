package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlumnoCursoId implements Serializable {
    private static final long serialVersionUID = 5059781843785958977L;
    @Column(name = "alumno_id", nullable = false)
    private Integer alumnoId;

    @Column(name = "curso_id", nullable = false)
    private Integer cursoId;

    public Integer getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AlumnoCursoId entity = (AlumnoCursoId) o;
        return Objects.equals(this.alumnoId, entity.alumnoId) &&
                Objects.equals(this.cursoId, entity.cursoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumnoId, cursoId);
    }

}