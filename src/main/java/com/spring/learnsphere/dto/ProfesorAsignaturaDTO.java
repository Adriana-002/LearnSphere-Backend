package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfesorAsignaturaDTO {
    private Integer profesorId;
    private Integer asignaturaId;
}
