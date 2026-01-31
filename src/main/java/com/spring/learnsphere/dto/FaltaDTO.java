package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaltaDTO {
    private Long faltaId;
    private Long alumnoId;
    private LocalDate fecha;
    private LocalTime hora;
}
