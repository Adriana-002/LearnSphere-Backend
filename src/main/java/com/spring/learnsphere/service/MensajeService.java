package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Mensaje;
import com.spring.learnsphere.repository.MensajeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    public List<Mensaje> findAllMensajes() {
        return mensajeRepository.findAll();
    }

    public Mensaje findMensajeById(Integer id) {
        return mensajeRepository.findById(id).orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
    }

    public Mensaje createMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public Mensaje updateMensaje(Integer id, Mensaje mensajeDetails) {
        Mensaje mensaje = findMensajeById(id);

        if (mensaje == null) {
            throw new IllegalArgumentException("Mensaje no encontrado");
        }
        mensaje.setChat(mensajeDetails.getChat());
        mensaje.setUser(mensajeDetails.getUser());
        mensaje.setTexto(mensajeDetails.getTexto());
        mensaje.setFechaEnvio(mensajeDetails.getFechaEnvio());
        return createMensaje(mensaje);
    }

    public void deleteMensaje(Integer id) {
        if (findMensajeById(id) == null) {
            throw new IllegalArgumentException("Mensaje no encontrado");
        }
        mensajeRepository.deleteById(id);
    }

}
