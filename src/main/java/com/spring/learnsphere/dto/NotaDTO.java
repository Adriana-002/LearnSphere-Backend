package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDTO {
    private Integer notaId;
    private Integer alumnoId;
    private Integer cursoAsignaturaId;
    private Integer trimestre;
    private Double calificacion;
    private String fechaRegistro;
    private String asignaturaNombre;
}
