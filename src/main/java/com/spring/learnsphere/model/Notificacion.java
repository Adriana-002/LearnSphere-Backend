package com.spring.learnsphere.model;

import com.spring.learnsphere.enums.TipoNotificacion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacion_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, columnDefinition = "tipo_notificacion")
    private TipoNotificacion tipo;

    @Column(name = "mensaje", nullable = false, length = 200)
    private String mensaje;

    @Column(name = "entidad_id")
    private Integer entidadId;

    @Column(name = "entidad_tipo", length = 30)
    private String entidadTipo;

    @ColumnDefault("false")
    @Column(name = "leida")
    private Boolean leida;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha")
    private Instant fecha;


}