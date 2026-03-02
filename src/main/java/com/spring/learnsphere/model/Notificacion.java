package com.spring.learnsphere.model;

import com.spring.learnsphere.enums.TipoNotificacion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

/**
 * Entidad que representa una notificación en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "notificaciones" de la base de datos y contiene
 * las notificaciones que se envían a los usuarios sobre eventos importantes en el sistema.
 *
 * @author Adriana
 */
@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Notificacion {
    /**
     * Identificador único de la notificación.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacion_id", nullable = false)
    private Integer id;

    /**
     * Usuario destinatario de la notificación.
     * Relación ManyToOne con la entidad Usuario.
     * Campo obligatorio y la eliminación en cascada garantiza que al eliminar un usuario
     * se eliminen todas sus notificaciones.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    /**
     * Tipo de notificación.
     * Campo obligatorio que especifica la categoría o naturaleza de la notificación.
     * Utiliza un enum TipoNotificacion para las opciones válidas.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, columnDefinition = "tipo_notificacion")
    private TipoNotificacion tipo;

    /**
     * Mensaje o contenido de la notificación.
     * Campo obligatorio con una longitud máxima de 200 caracteres.
     * Contiene el texto descriptivo de la notificación.
     */
    @Column(name = "mensaje", nullable = false, length = 200)
    private String mensaje;

    /**
     * ID de la entidad relacionada con la notificación.
     * Campo opcional que almacena el ID de la entidad (alumno, nota, observación, etc.)
     * que generó la notificación.
     */
    @Column(name = "entidad_id")
    private Integer entidadId;

    /**
     * Tipo de entidad relacionada con la notificación.
     * Campo opcional con una longitud máxima de 30 caracteres.
     * Ejemplos: 'NOTA', 'ALUMNO', 'OBSERVACION', 'FALTA', etc.
     */
    @Column(name = "entidad_tipo", length = 30)
    private String entidadTipo;

    /**
     * Indica si la notificación ha sido leída.
     * Campo booleano que por defecto es false.
     * Se actualiza cuando el usuario ve la notificación.
     */
    @ColumnDefault("false")
    @Column(name = "leida")
    private Boolean leida;

    /**
     * Fecha de creación de la notificación.
     * Se establece automáticamente a la fecha y hora actual al crear la notificación.
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha")
    private Instant fecha;

}