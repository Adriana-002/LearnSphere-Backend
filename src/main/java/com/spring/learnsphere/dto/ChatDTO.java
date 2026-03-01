package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatDTO {
    private Integer chatId;
    private String tipo;
    private String nombreChat;
    private String ultimoMensaje;
    private String tiempoUltimoMensaje;
    private Integer creadorId;
    private String nombreAlumno;
    private String nombreAsignatura;
    @JsonAlias("usuarioIds")
    private List<Integer> userIds;
}
