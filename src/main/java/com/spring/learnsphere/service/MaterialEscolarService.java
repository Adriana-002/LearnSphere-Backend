package com.spring.learnsphere.service;

import com.spring.learnsphere.dto.MaterialEscolarDTO;
import com.spring.learnsphere.enums.TipoNotificacion;
import com.spring.learnsphere.model.*;
import com.spring.learnsphere.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MaterialEscolarService {

    private final MaterialEscolarRepository materialRepository;
    private final CursoMaterialRepository cursoMaterialRepository;
    private final CursoRepository cursoRepository;
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;
    private final CursoAsignaturaRepository cursoAsignaturaRepository;
    private final NotificacionRepository notificacionRepository;
    private final AlumnoCursoRepository alumnoCursoRepository;
    private final TutorAlumnoRepository tutorAlumnoRepository;

    public List<MaterialEscolarDTO> getAll() {
        return materialRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MaterialEscolarDTO> getByCurso(Integer cursoId) {
        return cursoMaterialRepository.findAll().stream()
                .filter(cm -> cm.getCurso().getId().equals(cursoId))
                .map(cm -> toDTO(cm.getMaterial()))
                .collect(Collectors.toList());
    }

    public List<MaterialEscolarDTO> getByProfesor(Integer profesorId) {
        List<Integer> cursoIds = profesorAsignaturaRepository.findByProfesor_Id(profesorId)
                .stream()
                .flatMap(pa -> cursoAsignaturaRepository.findAll().stream()
                        .filter(ca -> ca.getAsignatura().getId().equals(pa.getAsignatura().getId()))
                        .map(ca -> ca.getCurso().getId()))
                .distinct()
                .collect(Collectors.toList());

        return cursoMaterialRepository.findAll().stream()
                .filter(cm -> cursoIds.contains(cm.getCurso().getId()))
                .map(cm -> toDTO(cm.getMaterial()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Transactional
    public MaterialEscolarDTO createMaterial(MaterialEscolarDTO dto) {
        log.info("Creando material escolar: {} con cursoId: {}", dto.getNombre(), dto.getCursoId());

        MaterialEscolar m = new MaterialEscolar();
        m.setNombre(dto.getNombre());
        m.setEditorial(dto.getEditorial());
        m.setIsbn(dto.getIsbn());
        MaterialEscolar saved = materialRepository.save(m);
        log.info("Material guardado con ID: {}", saved.getId());

        if (dto.getCursoId() != null) {
            log.info("Procesando curso ID: {}", dto.getCursoId());

            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

            CursoMaterialId cmId = new CursoMaterialId();
            cmId.setCursoId(curso.getId());
            cmId.setMaterialId(saved.getId());

            CursoMaterial cm = new CursoMaterial();
            cm.setId(cmId);
            cm.setCurso(curso);
            cm.setMaterial(saved);
            cursoMaterialRepository.save(cm);
            log.info("Relación curso-material guardada");

            List<AlumnoCurso> alumnosCurso = alumnoCursoRepository.findByCursoId(curso.getId());
            log.info("Encontrados {} alumnos en el curso {}", alumnosCurso.size(), curso.getNombre());

            int notificacionesCreadas = 0;
            for (AlumnoCurso alumnoCurso : alumnosCurso) {
                log.debug("Procesando alumno ID: {} - {}", alumnoCurso.getAlumno().getId(), alumnoCurso.getAlumno().getNombre());

                TutorAlumno tutorAlumno = tutorAlumnoRepository.findAllByAlumnoId(alumnoCurso.getAlumno().getId());
                if (tutorAlumno != null) {
                    log.info("Tutor encontrado para alumno {}: Tutor ID {}",
                            alumnoCurso.getAlumno().getNombre(), tutorAlumno.getTutor().getId());

                    Notificacion notificacion = new Notificacion();
                    notificacion.setUser(tutorAlumno.getTutor().getUsuarios());
                    notificacion.setTipo(TipoNotificacion.recordatorio_material);
                    notificacion.setMensaje("Nuevo material escolar para el curso " + curso.getNombre() +
                                           ": " + saved.getNombre());
                    notificacion.setEntidadId(saved.getId());
                    notificacion.setEntidadTipo("material");
                    notificacion.setLeida(false);
                    notificacion.setFecha(java.time.Instant.now());

                    Notificacion notifSaved = notificacionRepository.save(notificacion);
                    notificacionesCreadas++;
                    log.info("Notificación creada con ID: {} para usuario ID: {} (tutor de {})",
                             notifSaved.getId(), notifSaved.getUser().getId(), alumnoCurso.getAlumno().getNombre());
                } else {
                    log.warn("No se encontró tutor legal para el alumno ID: {} - {}",
                            alumnoCurso.getAlumno().getId(), alumnoCurso.getAlumno().getNombre());
                }
            }
            log.info("Se crearon {} notificaciones para el material {}", notificacionesCreadas, saved.getNombre());
        } else {
            log.warn("No se proporcionó cursoId, no se crearán notificaciones para el material {}", saved.getNombre());
        }

        MaterialEscolarDTO result = toDTO(saved);
        result.setCursoId(dto.getCursoId());
        return result;
    }

    @Transactional
    public MaterialEscolarDTO updateMaterial(Integer materialId, MaterialEscolarDTO dto) {
        log.info("Actualizando material escolar con ID: {}", materialId);

        MaterialEscolar material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material escolar no encontrado con ID: " + materialId));

        if (dto.getNombre() != null && !dto.getNombre().isEmpty()) {
            material.setNombre(dto.getNombre());
        }
        if (dto.getEditorial() != null && !dto.getEditorial().isEmpty()) {
            material.setEditorial(dto.getEditorial());
        }
        if (dto.getIsbn() != null && !dto.getIsbn().isEmpty()) {
            material.setIsbn(dto.getIsbn());
        }

        MaterialEscolar updated = materialRepository.save(material);
        log.info("Material escolar actualizado con ID: {}", updated.getId());

        // Si se proporciona un cursoId, asegurar que exista la relación curso-material
        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + dto.getCursoId()));

            CursoMaterialId cmId = new CursoMaterialId();
            cmId.setCursoId(curso.getId());
            cmId.setMaterialId(updated.getId());

            boolean existeRelacion = cursoMaterialRepository.existsById(cmId);
            if (!existeRelacion) {
                CursoMaterial cm = new CursoMaterial();
                cm.setId(cmId);
                cm.setCurso(curso);
                cm.setMaterial(updated);
                cursoMaterialRepository.save(cm);
                log.info("Nueva relación curso-material creada para cursoId: {}", curso.getId());
            }
        }

        // Crear notificaciones para TODOS los cursos asociados al material
        crearNotificacionesMaterial(updated, "actualizado");

        MaterialEscolarDTO result = toDTO(updated);
        result.setCursoId(dto.getCursoId());
        return result;
    }

    @Transactional
    public void deleteMaterial(Integer materialId) {
        log.info("Eliminando material escolar con ID: {}", materialId);

        MaterialEscolar material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material escolar no encontrado con ID: " + materialId));

        List<CursoMaterial> cursoMateriales = cursoMaterialRepository.findByMaterialId(materialId);

        for (CursoMaterial cm : cursoMateriales) {
            crearNotificacionesMaterialPorCurso(cm.getCurso(), material, "eliminado");
        }

        for (CursoMaterial cm : cursoMateriales) {
            cursoMaterialRepository.delete(cm);
            log.info("Relación curso-material eliminada para cursoId: {}", cm.getCurso().getId());
        }

        materialRepository.delete(material);
        log.info("Material escolar eliminado con ID: {}", materialId);
    }

    /**
     * Crea notificaciones para todos los tutores de alumnos de los cursos asociados al material
     */
    private void crearNotificacionesMaterial(MaterialEscolar material, String accion) {
        List<CursoMaterial> cursoMateriales = cursoMaterialRepository.findByMaterialId(material.getId());
        log.info("Encontradas {} relaciones curso-material para materialId: {}", cursoMateriales.size(), material.getId());

        for (CursoMaterial cm : cursoMateriales) {
            crearNotificacionesMaterialPorCurso(cm.getCurso(), material, accion);
        }
    }

    /**
     * Crea notificaciones para los tutores de los alumnos de un curso específico
     */
    private void crearNotificacionesMaterialPorCurso(Curso curso, MaterialEscolar material, String accion) {
        log.info("Creando notificaciones para material {} ({})", material.getId(), accion);

        List<AlumnoCurso> alumnosCurso = alumnoCursoRepository.findByCursoId(curso.getId());
        log.info("Encontrados {} alumnos en el curso {}", alumnosCurso.size(), curso.getNombre());

        int notificacionesCreadas = 0;
        for (AlumnoCurso alumnoCurso : alumnosCurso) {
            log.debug("Procesando alumno ID: {} - {}", alumnoCurso.getAlumno().getId(), alumnoCurso.getAlumno().getNombre());

            TutorAlumno tutorAlumno = tutorAlumnoRepository.findAllByAlumnoId(alumnoCurso.getAlumno().getId());
            if (tutorAlumno != null) {
                log.info("Tutor encontrado para alumno {}: Tutor ID {}",
                        alumnoCurso.getAlumno().getNombre(), tutorAlumno.getTutor().getId());

                Notificacion notificacion = new Notificacion();
                notificacion.setUser(tutorAlumno.getTutor().getUsuarios());
                notificacion.setTipo(TipoNotificacion.recordatorio_material);

                String mensajeAccion = "";
                if ("actualizado".equals(accion)) {
                    mensajeAccion = "El material escolar \"" + material.getNombre() + "\" del curso " + curso.getNombre() + " ha sido actualizado.";
                } else if ("eliminado".equals(accion)) {
                    mensajeAccion = "El material escolar \"" + material.getNombre() + "\" del curso " + curso.getNombre() + " ha sido eliminado.";
                }

                notificacion.setMensaje(mensajeAccion);
                notificacion.setEntidadId(material.getId());
                notificacion.setEntidadTipo("material");
                notificacion.setLeida(false);
                notificacion.setFecha(java.time.Instant.now());

                Notificacion notifSaved = notificacionRepository.save(notificacion);
                notificacionesCreadas++;
                log.info("Notificación creada con ID: {} para usuario ID: {} (tutor de {})",
                         notifSaved.getId(), notifSaved.getUser().getId(), alumnoCurso.getAlumno().getNombre());
            } else {
                log.warn("No se encontró tutor legal para el alumno ID: {} - {}",
                        alumnoCurso.getAlumno().getId(), alumnoCurso.getAlumno().getNombre());
            }
        }
        log.info("Se crearon {} notificaciones para el material {} ({})", notificacionesCreadas, material.getNombre(), accion);
    }

    private MaterialEscolarDTO toDTO(MaterialEscolar m) {
        MaterialEscolarDTO dto = new MaterialEscolarDTO();
        dto.setMaterialId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setEditorial(m.getEditorial());
        dto.setIsbn(m.getIsbn());
        return dto;
    }


}
