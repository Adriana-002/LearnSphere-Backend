package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta de la entidad TutorAlumno.
 *
 * Esta clase mapea la clave primaria compuesta que combina el ID del tutor legal
 * y el ID del alumno. Se utiliza como tipo embebido en la entidad TutorAlumno.
 *
 * @author Adriana
 */
@Embeddable
public class TutorAlumnoId implements Serializable {
    private static final long serialVersionUID = -6375904916055245123L;

    /**
     * ID del tutor legal que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "tutor_id", nullable = false)
    private Integer tutorId;

    /**
     * ID del alumno que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "alumno_id", nullable = false)
    private Integer alumnoId;

    /**
     * Obtiene el ID del tutor legal.
     * @return ID del tutor legal
     */
    public Integer getTutorId() {
        return tutorId;
    }

    /**
     * Establece el ID del tutor legal.
     * @param tutorId ID del tutor legal a establecer
     */
    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    /**
     * Obtiene el ID del alumno.
     * @return ID del alumno
     */
    public Integer getAlumnoId() {
        return alumnoId;
    }

    /**
     * Establece el ID del alumno.
     * @param alumnoId ID del alumno a establecer
     */
    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    /**
     * Compara esta instancia con otro objeto para igualdad.
     * @param o objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TutorAlumnoId entity = (TutorAlumnoId) o;
        return Objects.equals(this.tutorId, entity.tutorId) &&
                Objects.equals(this.alumnoId, entity.alumnoId);
    }

    /**
     * Calcula el hash de esta instancia.
     * @return código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(tutorId, alumnoId);
    }

}