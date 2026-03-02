package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que representa a un tutor legal en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "tutores_legales" de la base de datos y extiende
 * la información del usuario con datos específicos de tutores legales, incluyendo
 * si el tutor es también alumno del sistema.
 *
 * Se relaciona con la entidad Usuario mediante herencia (una relación OneToOne).
 *
 * @author Adriana
 */
@Entity
@Table(name = "tutores_legales")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TutorLegal {
    /**
     * Identificador único del tutor legal.
     * Corresponde al ID del usuario asociado (clave foránea).
     */
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    /**
     * Referencia a la entidad Usuario asociada.
     * Relación OneToOne que se mapea con el identificador del tutor.
     * La eliminación en cascada garantiza que al eliminar un usuario se elimine el tutor.
     */
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuarios;

    /**
     * Indica si el tutor legal es también alumno del sistema.
     * Campo booleano que por defecto es false.
     * Permite identificar tutores que tengan doble rol en la plataforma.
     */
    @ColumnDefault("false")
    @Column(name = "es_alumno")
    private Boolean esAlumno;

}