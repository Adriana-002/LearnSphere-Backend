package com.spring.learnsphere.dto;

import com.spring.learnsphere.enums.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificacionDTO {
    private Long notificacionId;
    private Long userId;
    private TipoNotificacion tipo;
    private String mensaje;
    private Long entidadId;
    private String entidadTipo;
    private Boolean leida;
    private LocalDateTime fecha;
}
