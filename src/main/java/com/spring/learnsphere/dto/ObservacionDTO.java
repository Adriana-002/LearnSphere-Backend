package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObservacionDTO {
    private Integer observacionId;
    private Integer alumnoId;
    private Integer asignaturaId;
    private Integer trimestre;
    private String mensaje;
    private Integer profesorId;
    private String fecha;
    private String profesorNombre;
    private String asignaturaNombre;
}
