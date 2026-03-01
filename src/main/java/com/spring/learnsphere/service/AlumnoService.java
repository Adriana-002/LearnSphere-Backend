package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.AlumnoDTO;
import com.spring.learnsphere.model.*;
import com.spring.learnsphere.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final TutorAlumnoRepository tutorAlumnoRepository;
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;
    private final CursoAsignaturaRepository cursoAsignaturaRepository;
    private final AlumnoCursoRepository alumnoCursoRepository;

    public List<AlumnoDTO> getAllAlumnos() {
        return alumnoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AlumnoDTO findAlumnoById(Integer id) {
        return toDTO(alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado")));
    }

    public AlumnoDTO getByTutor(Integer tutorId) {
        return tutorAlumnoRepository.findAllByTutor_Id(tutorId)
                .stream()
                .map(ta -> toDTO(ta.getAlumno()))
                .findFirst()
                .orElse(null);
    }

    public List<AlumnoDTO> getByProfesor(Integer profesorId) {
        List<Integer> asignaturaIds = profesorAsignaturaRepository.findByProfesor_Id(profesorId)
                .stream()
                .map(pa -> pa.getAsignatura().getId())
                .collect(Collectors.toList());

        List<Integer> alumnoIds = cursoAsignaturaRepository.findAll().stream()
                .filter(ca -> asignaturaIds.contains(ca.getAsignatura().getId()))
                .flatMap(ca -> alumnoCursoRepository.findAll().stream()
                        .filter(ac -> ac.getCurso().getId().equals(ca.getCurso().getId())))
                .map(ac -> ac.getAlumno().getId())
                .distinct()
                .collect(Collectors.toList());

        return alumnoRepository.findAllById(alumnoIds)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AlumnoDTO> getByCurso(Integer cursoId) {
        return alumnoCursoRepository.findByCursoId(cursoId).stream()
                .map(ac -> toDTO(ac.getAlumno()))
                .collect(Collectors.toList());
    }

    public AlumnoDTO createAlumno(AlumnoDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        return toDTO(alumnoRepository.save(alumno));
    }

    public AlumnoDTO updateAlumno(Integer id, AlumnoDTO dto) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        return toDTO(alumnoRepository.save(alumno));
    }

    public void deleteAlumno(Integer id) {
        alumnoRepository.deleteById(id);
    }

    private AlumnoDTO toDTO(Alumno a) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.setAlumnoId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setApellidos(a.getApellidos());
        dto.setFechaNacimiento(a.getFechaNacimiento() != null ? a.getFechaNacimiento().toString() : null);
        dto.setFotoUrl(a.getFotoUrl());
        // Buscar el curso del alumno
        alumnoCursoRepository.findAll().stream()
                .filter(ac -> ac.getAlumno().getId().equals(a.getId()))
                .findFirst()
                .ifPresent(ac -> dto.setCursoId(ac.getCurso().getId()));
        return dto;
    }

}
