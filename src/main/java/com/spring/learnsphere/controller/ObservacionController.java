package com.spring.learnsphere.controller;

import com.spring.learnsphere.model.Observacion;
import com.spring.learnsphere.service.ObservacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/observaciones")
public class ObservacionController {

    private final ObservacionService observacionService;

    @GetMapping("/listar")
    public List<Observacion> listarObservaciones() {
        return observacionService.findAllObservaciones();
    }

    @GetMapping("/buscar/{id}")
    public Observacion buscarObservacionPorId(@PathVariable Integer id) {
        return observacionService.findObservacionById(id);
    }

    @PostMapping("/crear")
    public Observacion crearObservacion(@RequestBody Observacion observacion) {
        return observacionService.createObservacion(observacion);
    }

    @PutMapping("/editar/{id}")
    public Observacion editarObservacion(@PathVariable Integer id, @RequestBody Observacion observacion) {
        return observacionService.updateObservacion(id, observacion);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarObservacion(@PathVariable Integer id) {
        observacionService.deleteObservacion(id);
    }

}
