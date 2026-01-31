package com.spring.learnsphere.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "profesor_asignatura")
public class ProfesorAsignatura {
    @EmbeddedId
    private ProfesorAsignaturaId id;

    @MapsId("profesorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @MapsId("asignaturaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    public ProfesorAsignaturaId getId() {
        return id;
    }

    public void setId(ProfesorAsignaturaId id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

}