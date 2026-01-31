package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
    private Long userId;
    private String email;
    private String passwordHash;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String rol;
    private LocalDateTime fechaRegistro;
}
