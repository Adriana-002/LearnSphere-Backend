package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoAsignaturaDTO {
    private Integer cursoAsignaturaId;
    private Integer cursoId;
    private Integer asignaturaId;
}
