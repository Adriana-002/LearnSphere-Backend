package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaltaDTO {
    private Integer faltaId;
    private Integer alumnoId;
    private String fecha;
    private String hora;
}
