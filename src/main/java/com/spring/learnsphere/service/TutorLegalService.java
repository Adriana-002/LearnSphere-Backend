package com.spring.learnsphere.service;

import com.spring.learnsphere.model.TutorLegal;
import com.spring.learnsphere.repository.TutorLegalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TutorLegalService {

    private final TutorLegalRepository tutorLegalRepository;

    public List<TutorLegal> findAllTutoresLegales() {
        return tutorLegalRepository.findAll();
    }

    public TutorLegal findTutorLegalById(Integer id) {
        return tutorLegalRepository.findById(id).orElseThrow(() -> new RuntimeException("Tutor legal no encontrado"));
    }

    public TutorLegal createTutorLegal(TutorLegal tutorLegal) {
        return tutorLegalRepository.save(tutorLegal);
    }

    public TutorLegal updateTutorLegal(Integer id, TutorLegal tutorLegalDetails) {
        TutorLegal tutorLegal = findTutorLegalById(id);

        if (tutorLegal == null) {
            throw new IllegalArgumentException("Tutor legal no encontrado");
        }
        tutorLegal.setUsuarios(tutorLegalDetails.getUsuarios());
        tutorLegal.setEsAlumno(tutorLegalDetails.getEsAlumno());
        return createTutorLegal(tutorLegal);
    }

    public void deleteTutorLegal(Integer id) {
        if (findTutorLegalById(id) == null) {
            throw new IllegalArgumentException("Tutor legal no encontrado");
        }
        tutorLegalRepository.deleteById(id);
    }

}
