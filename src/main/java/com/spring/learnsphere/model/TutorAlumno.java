package com.spring.learnsphere.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tutor_alumno")
public class TutorAlumno {
    @EmbeddedId
    private TutorAlumnoId id;

    @MapsId("tutorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tutor_id", nullable = false)
    private TutorLegal tutor;

    @MapsId("alumnoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @Column(name = "parentesco", length = 50)
    private String parentesco;

    public TutorAlumnoId getId() {
        return id;
    }

    public void setId(TutorAlumnoId id) {
        this.id = id;
    }

    public TutorLegal getTutor() {
        return tutor;
    }

    public void setTutor(TutorLegal tutor) {
        this.tutor = tutor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

}