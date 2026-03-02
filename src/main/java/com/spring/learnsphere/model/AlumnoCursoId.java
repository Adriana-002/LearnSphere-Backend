package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta de la entidad AlumnoCurso.
 *
 * Esta clase mapea la clave primaria compuesta que combina el ID del alumno
 * y el ID del curso. Se utiliza como tipo embebido en la entidad AlumnoCurso.
 *
 * @author Adriana
 */
@Embeddable
public class AlumnoCursoId implements Serializable {
    private static final long serialVersionUID = 5059781843785958977L;

    /**
     * ID del alumno que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "alumno_id", nullable = false)
    private Integer alumnoId;

    /**
     * ID del curso que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "curso_id", nullable = false)
    private Integer cursoId;

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
     * Obtiene el ID del curso.
     * @return ID del curso
     */
    public Integer getCursoId() {
        return cursoId;
    }

    /**
     * Establece el ID del curso.
     * @param cursoId ID del curso a establecer
     */
    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
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
        AlumnoCursoId entity = (AlumnoCursoId) o;
        return Objects.equals(this.alumnoId, entity.alumnoId) &&
                Objects.equals(this.cursoId, entity.cursoId);
    }

    /**
     * Calcula el hash de esta instancia.
     * @return código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(alumnoId, cursoId);
    }

}