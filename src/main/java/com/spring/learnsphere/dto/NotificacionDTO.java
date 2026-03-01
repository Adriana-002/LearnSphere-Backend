package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificacionDTO {
    private Integer notificacionId;
    private Integer userId;
    private String tipo;
    private String mensaje;
    private Boolean leida;
    private String fecha;
}
