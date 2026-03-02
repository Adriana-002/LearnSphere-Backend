package com.spring.learnsphere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta de la entidad CursoMaterial.
 *
 * Esta clase mapea la clave primaria compuesta que combina el ID del curso
 * y el ID del material. Se utiliza como tipo embebido en la entidad CursoMaterial.
 *
 * @author Adriana
 */
@Embeddable
public class CursoMaterialId implements Serializable {
    private static final long serialVersionUID = 3308977156672251923L;

    /**
     * ID del curso que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "curso_id", nullable = false)
    private Integer cursoId;

    /**
     * ID del material que forma parte de la clave primaria compuesta.
     * Campo obligatorio.
     */
    @Column(name = "material_id", nullable = false)
    private Integer materialId;

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
     * Obtiene el ID del material.
     * @return ID del material
     */
    public Integer getMaterialId() {
        return materialId;
    }

    /**
     * Establece el ID del material.
     * @param materialId ID del material a establecer
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
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
        CursoMaterialId entity = (CursoMaterialId) o;
        return Objects.equals(this.cursoId, entity.cursoId) &&
                Objects.equals(this.materialId, entity.materialId);
    }

    /**
     * Calcula el hash de esta instancia.
     * @return código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(cursoId, materialId);
    }

}