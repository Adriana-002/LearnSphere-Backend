package com.spring.learnsphere.service;

import com.spring.learnsphere.model.Curso;
import com.spring.learnsphere.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public List<Curso> findAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso findCursoById(Integer id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Integer id, Curso cursoDetails) {
        Curso curso = findCursoById(id);

        if (curso == null) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        curso.setNombre(cursoDetails.getNombre());
        curso.setAñoAcademico(cursoDetails.getAñoAcademico());
        return createCurso(curso);
    }

    public void deleteCurso(Integer id) {
        if (findCursoById(id) == null) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        cursoRepository.deleteById(id);
    }

}
