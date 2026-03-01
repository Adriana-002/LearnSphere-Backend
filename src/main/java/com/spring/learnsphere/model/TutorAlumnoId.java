package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TutorAlumnoId implements Serializable {
    private static final long serialVersionUID = -6375904916055245123L;
    @Column(name = "tutor_id", nullable = false)
    private Integer tutorId;

    @Column(name = "alumno_id", nullable = false)
    private Integer alumnoId;

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public Integer getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TutorAlumnoId entity = (TutorAlumnoId) o;
        return Objects.equals(this.tutorId, entity.tutorId) &&
                Objects.equals(this.alumnoId, entity.alumnoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorId, alumnoId);
    }

}