package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
    @JsonProperty("user_id")
    private Integer userId;
    private String email;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String rol;
    private String fechaRegistro;
}
