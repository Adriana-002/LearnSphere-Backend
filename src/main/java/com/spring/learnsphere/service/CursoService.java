package com.spring.learnsphere.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.spring.learnsphere.model.Curso;
import com.spring.learnsphere.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerCursoPorId(Integer id) {
        return cursoRepository.findById(id);
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void eliminarCurso(Integer id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> listarPorAñoAcademico(String añoAcademico) {
        return cursoRepository.findByAñoAcademico(añoAcademico);
    }
}
