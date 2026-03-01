package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfesorDTO {
    private Integer userId;
    private String nombre;
    private String apellidos;
    private String email;
    private String departamento;
}
