package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta de la entidad ProfesorAsignatura.
 *
 * Esta clase mapea la clave primaria compuesta que combina el ID del profesor
 * y el ID de la asignatura. Se utiliza como tipo embebido en la entidad ProfesorAsignatura.
 *
 * @author Adriana
 */
@Embeddable
public class ProfesorAsignaturaId implements Serializable {
    private static final long serialVersionUID = 8706772925523668369L;

    /**
     * ID del profesor que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "profesor_id", nullable = false)
    private Integer profesorId;

    /**
     * ID de la asignatura que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "asignatura_id", nullable = false)
    private Integer asignaturaId;

    /**
     * Obtiene el ID del profesor.
     * @return ID del profesor
     */
    public Integer getProfesorId() {
        return profesorId;
    }

    /**
     * Establece el ID del profesor.
     * @param profesorId ID del profesor a establecer
     */
    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    /**
     * Obtiene el ID de la asignatura.
     * @return ID de la asignatura
     */
    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    /**
     * Establece el ID de la asignatura.
     * @param asignaturaId ID de la asignatura a establecer
     */
    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
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
        ProfesorAsignaturaId entity = (ProfesorAsignaturaId) o;
        return Objects.equals(this.asignaturaId, entity.asignaturaId) &&
                Objects.equals(this.profesorId, entity.profesorId);
    }

    /**
     * Calcula el hash de esta instancia.
     * @return código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(asignaturaId, profesorId);
    }

}