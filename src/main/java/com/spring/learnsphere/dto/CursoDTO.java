package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoDTO {
    private Long cursoId;
    private String nombre;
    private String añoAcademico;
}
