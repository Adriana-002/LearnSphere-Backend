package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MensajeDTO {
    private Long mensajeId;
    private Long chatId;
    private Long userId;
    private String texto;
    private LocalDateTime fechaEnvio;
}
