package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * Entidad que representa una conversación de chat en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "chats" de la base de datos y contiene
 * la información de las conversaciones de chat que pueden ser individuales o de grupo.
 *
 * @author Adriana
 */
@Entity
@Table(name = "chats")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Chat {
    /**
     * Identificador único del chat.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    private Integer id;

    /**
     * Tipo de chat.
     * Campo que especifica si el chat es individual o de grupo.
     * Por defecto es 'individual' con una longitud máxima de 20 caracteres.
     * Valores válidos: 'individual', 'grupo'.
     */
    @ColumnDefault("'individual'")
    @Column(name = "tipo", length = 20)
    private String tipo;

    /**
     * Fecha de creación del chat.
     * Se establece automáticamente a la fecha y hora actual al crear el chat.
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}