package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MensajeDTO {
    private Integer mensajeId;
    private Integer chatId;
    private Integer userId;
    private String texto;
    private String fechaEnvio;
    private String nombreRemitente;
}
