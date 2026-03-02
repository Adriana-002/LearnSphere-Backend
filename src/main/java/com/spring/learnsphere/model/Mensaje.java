package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * Entidad que representa un mensaje dentro de un chat en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "mensajes" de la base de datos y contiene
 * los mensajes individuales enviados en las conversaciones de chat.
 *
 * @author Adriana
 */
@Entity
@Table(name = "mensajes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Mensaje {
    /**
     * Identificador único del mensaje.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id", nullable = false)
    private Integer id;

    /**
     * Chat al que pertenece el mensaje.
     * Relación ManyToOne con la entidad Chat.
     * La eliminación en cascada garantiza que al eliminar un chat se eliminen sus mensajes.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    /**
     * Usuario que envía el mensaje.
     * Relación ManyToOne con la entidad Usuario.
     * La eliminación en cascada garantiza que al eliminar un usuario se eliminen sus mensajes.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private Usuario user;

    /**
     * Contenido o texto del mensaje.
     * Campo obligatorio que contiene el mensaje enviado.
     * Sin límite de longitud (Integer.MAX_VALUE).
     */
    @Column(name = "texto", nullable = false, length = Integer.MAX_VALUE)
    private String texto;

    /**
     * Fecha y hora de envío del mensaje.
     * Se establece automáticamente a la fecha y hora actual al crear el mensaje.
     */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

}