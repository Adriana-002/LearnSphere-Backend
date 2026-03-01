package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialEscolarDTO {
    private Integer materialId;
    private String nombre;
    private String editorial;
    private String isbn;
    private Integer cursoId;
}
