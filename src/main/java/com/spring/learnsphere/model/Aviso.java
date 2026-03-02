package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * Entidad que representa un aviso o comunicado en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "avisos" de la base de datos y contiene
 * los avisos generales o comunicados que se publican en el sistema educativo
 * para informar a la comunidad académica.
 *
 * @author Adriana
 */
@Entity
@Table(name = "avisos")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Aviso {
    /**
     * Identificador único del aviso.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aviso_id", nullable = false)
    private Integer id;

    /**
     * Título del aviso.
     * Campo obligatorio con una longitud máxima de 200 caracteres.
     * Debe ser descriptivo y conciso.
     */
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    /**
     * Contenido o cuerpo del aviso.
     * Campo obligatorio que contiene el mensaje detallado.
     * Sin límite de longitud (Integer.MAX_VALUE).
     */
    @Column(name = "mensaje", nullable = false, length = Integer.MAX_VALUE)
    private String mensaje;

    /**
     * Indica si el aviso es importante o prioritario.
     * Campo booleano que por defecto es false.
     * Permite resaltar avisos de mayor relevancia.
     */
    @ColumnDefault("false")
    @Column(name = "es_importante")
    private Boolean esImportante;

    /**
     * Fecha y hora de publicación del aviso.
     * Se establece automáticamente a la fecha y hora actual al crear el aviso.
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_publicacion")
    private Instant fechaPublicacion;

}