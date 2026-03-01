package com.spring.learnsphere.controller;

import com.spring.learnsphere.dto.AlumnoDTO;
import com.spring.learnsphere.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/listar")
    public List<AlumnoDTO> listarAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @GetMapping("/buscar/{id}")
    public AlumnoDTO buscarAlumnoPorId(@PathVariable Integer id) {
        return alumnoService.findAlumnoById(id);
    }

    @GetMapping("/tutor/{tutorId}")
    public AlumnoDTO getByTutor(@PathVariable Integer tutorId) {
        return alumnoService.getByTutor(tutorId);
    }

    @GetMapping("/profesor/{profesorId}")
    public List<AlumnoDTO> getByProfesor(@PathVariable Integer profesorId) {
        return alumnoService.getByProfesor(profesorId);
    }

    @GetMapping("/curso/{cursoId}")
    public List<AlumnoDTO> getByCurso(@PathVariable Integer cursoId) {
        return alumnoService.getByCurso(cursoId);
    }

    @PostMapping("/crear")
    public AlumnoDTO crearAlumno(@RequestBody AlumnoDTO dto) {
        return alumnoService.createAlumno(dto);
    }

    @PutMapping("/editar/{id}")
    public AlumnoDTO editarAlumno(@PathVariable Integer id, @RequestBody AlumnoDTO dto) {
        return alumnoService.updateAlumno(id, dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarAlumno(@PathVariable Integer id) {
        alumnoService.deleteAlumno(id);
    }

}
