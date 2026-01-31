package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnoDTO {
    private Long alumnoId;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String fotoUrl;
}
