package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa a un profesor en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "profesores" de la base de datos y extiende
 * la información del usuario con datos específicos de profesores, como el departamento.
 *
 * Se relaciona con la entidad Usuario mediante herencia (una relación OneToOne).
 *
 * @author Adriana
 */
@Entity
@Table(name = "profesores")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Profesor {
    /**
     * Identificador único del profesor.
     * Corresponde al ID del usuario asociado (clave foránea).
     */
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    /**
     * Referencia a la entidad Usuario asociada.
     * Relación OneToOne que se mapea con el identificador del profesor.
     * La eliminación en cascada garantiza que al eliminar un usuario se elimine el profesor.
     */
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuarios;

    /**
     * Departamento académico al que pertenece el profesor.
     * Campo obligatorio con una longitud máxima de 100 caracteres.
     * Ejemplo: Matemáticas, Lengua, Ciencias, etc.
     */
    @Column(name = "departamento", nullable = false, length = 100)
    private String departamento;

}