package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObservacionDTO {
    private Long observacionId;
    private Long alumnoId;
    private Long asignaturaId;
    private Long trimestre;
    private String mensaje;
    private Long profesorId;
    private LocalDateTime fecha;
}
