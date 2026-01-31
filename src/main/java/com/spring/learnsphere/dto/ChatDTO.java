package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatDTO {
    private Long chatId;
    private String tipo;
    private LocalDateTime fechaCreacion;
}
