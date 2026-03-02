package com.spring.learnsphere.enums;

/**
 * Enumeración que representa los tipos de notificación del sistema LearnSphere.
 *
 * Define las distintas categorías de notificaciones que pueden ser enviadas
 * a los usuarios de la plataforma, permitiendo clasificar y filtrar
 * las comunicaciones según su naturaleza.
 *
 * @author Adriana
 */
public enum TipoNotificacion {
    /** Notificación de una nueva nota registrada para un alumno. */
    nueva_nota,

    /** Notificación de una nueva falta de asistencia registrada. */
    nueva_falta,

    /** Notificación de una nueva observación sobre un alumno. */
    nueva_observacion,

    /** Notificación de un nuevo mensaje recibido en un chat. */
    nuevo_mensaje,

    /** Notificación de un nuevo aviso publicado. */
    nuevo_aviso,

    /** Notificación de recordatorio sobre material escolar necesario. */
    recordatorio_material
}
