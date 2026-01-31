package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDTO {
    private Long notaId;
    private Long alumnoId;
    private Long cursoAsignaturaId;
    private Long trimestre;
    private Double calificacion;
    private LocalDate fechaRegistro;
}
