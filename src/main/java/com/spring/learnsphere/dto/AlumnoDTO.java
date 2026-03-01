package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnoDTO {
    @JsonProperty("alumno_id")
    private Integer alumnoId;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellidos")
    private String apellidos;
    private String fechaNacimiento;
    private String fotoUrl;
    @JsonProperty("cursoId")
    private Integer cursoId;
}
