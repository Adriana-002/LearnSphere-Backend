package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoAsignaturaDTO {
    private Long cursoAsignaturaId;
    private Long cursoId;
    private Long asignaturaId;
}
