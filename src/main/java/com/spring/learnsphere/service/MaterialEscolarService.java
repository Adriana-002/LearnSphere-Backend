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

/**
 * Servicio que gestiona la lógica de negocio relacionada con los materiales escolares en el sistema LearnSphere.
 *
 * Proporciona operaciones CRUD y consultas específicas para la gestión de materiales escolares,
 * incluyendo la asociación con cursos y la generación de notificaciones automáticas
 * para los tutores legales de los alumnos afectados.
 *
 * @author Adriana
 */
@Service
@AllArgsConstructor
@Slf4j
public class MaterialEscolarService {

    /** Repositorio para acceder a los datos de materiales escolares. */
    private final MaterialEscolarRepository materialRepository;

    /** Repositorio para acceder a las relaciones curso-material. */
    private final CursoMaterialRepository cursoMaterialRepository;

    /** Repositorio para acceder a los datos de cursos. */
    private final CursoRepository cursoRepository;

    /** Repositorio para acceder a las relaciones profesor-asignatura. */
    private final ProfesorAsignaturaRepository profesorAsignaturaRepository;

    /** Repositorio para acceder a las relaciones curso-asignatura. */
    private final CursoAsignaturaRepository cursoAsignaturaRepository;

    /** Repositorio para acceder a los datos de notificaciones. */
    private final NotificacionRepository notificacionRepository;

    /** Repositorio para acceder a las relaciones alumno-curso. */
    private final AlumnoCursoRepository alumnoCursoRepository;

    /** Repositorio para acceder a las relaciones tutor-alumno. */
    private final TutorAlumnoRepository tutorAlumnoRepository;

    /**
     * Obtiene la lista de todos los materiales escolares del sistema.
     *
     * @return lista de DTOs con la información de todos los materiales
     */
    public List<MaterialEscolarDTO> getAll() {
        return materialRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene la lista de materiales escolares asociados a un curso.
     *
     * @param cursoId identificador del curso
     * @return lista de DTOs con la información de los materiales del curso
     */
    public List<MaterialEscolarDTO> getByCurso(Integer cursoId) {
        return cursoMaterialRepository.findAll().stream()
                .filter(cm -> cm.getCurso().getId().equals(cursoId))
                .map(cm -> toDTO(cm.getMaterial()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene la lista de materiales escolares asociados a los cursos de un profesor.
     *
     * @param profesorId identificador del profesor
     * @return lista de DTOs con la información de los materiales del profesor
     */
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

    /**
     * Crea un nuevo material escolar y lo asocia al curso indicado.
     *
     * Genera notificaciones automáticas para los tutores legales de los alumnos
     * del curso al que se asocia el material.
     *
     * @param dto datos del material a crear, incluyendo el ID del curso
     * @return DTO con la información del material creado
     * @throws RuntimeException si el curso indicado no existe
     */
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

    /**
     * Actualiza los datos de un material escolar existente.
     *
     * Si se proporciona un cursoId, asegura que exista la relación curso-material.
     * Genera notificaciones automáticas para los tutores legales de los alumnos
     * de todos los cursos asociados al material.
     *
     * @param materialId identificador del material a actualizar
     * @param dto        datos actualizados del material
     * @return DTO con la información del material actualizado
     * @throws RuntimeException si el material o el curso no existen
     */
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

        crearNotificacionesMaterial(updated, "actualizado");

        MaterialEscolarDTO result = toDTO(updated);
        result.setCursoId(dto.getCursoId());
        return result;
    }

    /**
     * Elimina un material escolar y sus relaciones con cursos.
     *
     * Genera notificaciones automáticas de eliminación para los tutores legales
     * de los alumnos de los cursos afectados antes de proceder con la eliminación.
     *
     * @param materialId identificador del material a eliminar
     * @throws RuntimeException si el material no existe
     */
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

    /**
     * Convierte una entidad MaterialEscolar a su correspondiente DTO.
     *
     * @param m entidad MaterialEscolar a convertir
     * @return DTO con la información del material escolar
     */
    private MaterialEscolarDTO toDTO(MaterialEscolar m) {
        MaterialEscolarDTO dto = new MaterialEscolarDTO();
        dto.setMaterialId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setEditorial(m.getEditorial());
        dto.setIsbn(m.getIsbn());
        return dto;
    }


}
