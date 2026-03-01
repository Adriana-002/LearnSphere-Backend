package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvisoDTO {
    private Integer avisoId;
    private String titulo;
    private String mensaje;
    private Boolean esImportante;
    private String fechaPublicacion;
}
