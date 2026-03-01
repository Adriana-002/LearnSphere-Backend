package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.MensajeDTO;
import com.spring.learnsphere.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    @GetMapping("/chat/{chatId}")
    public List<MensajeDTO> getByChatId(@PathVariable Integer chatId) {
        return mensajeService.getByChatId(chatId);
    }

    @PostMapping("/crear")
    public MensajeDTO createMensaje(@RequestBody MensajeDTO dto) {
        return mensajeService.createMensaje(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteMensaje(@PathVariable Integer id) {
        mensajeService.deleteMensaje(id);
    }

}
