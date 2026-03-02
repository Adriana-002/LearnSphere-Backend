package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa la transferencia de datos de un usuario.
 *
 * Este DTO se utiliza para transmitir información de usuarios entre
 * el servidor y el cliente en operaciones REST.
 *
 * @author Adriana
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
    /** Identificador único del usuario. */
    @JsonProperty("user_id")
    private Integer userId;

    /** Correo electrónico del usuario. */
    private String email;

    /** Nombre del usuario. */
    private String nombre;

    /** Apellidos del usuario. */
    private String apellidos;

    /** Número de teléfono del usuario. */
    private String telefono;

    /** Rol del usuario en el sistema. */
    private String rol;

    /** Fecha de registro del usuario. */
    private String fechaRegistro;
}
