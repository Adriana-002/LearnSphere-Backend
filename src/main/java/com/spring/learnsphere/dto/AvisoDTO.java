package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvisoDTO {
    private Long avisoId;
    private String titulo;
    private String mensaje;
    private Boolean esImportante;
    private LocalDateTime fechaPublicacion;
}
