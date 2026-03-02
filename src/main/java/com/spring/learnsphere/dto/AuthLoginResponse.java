package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO que representa la respuesta de autenticación al realizar login.
 *
 * <p>Este DTO contiene el token JWT y la información básica del usuario
 * autenticado que se envía al cliente tras un login exitoso.</p>
 *
 * @author Adriana
 */
public class AuthLoginResponse {
    /** Token JWT para autenticación. */
    private String token;

    /** Identificador único del usuario autenticado. */
    @JsonProperty("user_id")
    private Integer userId;

    /** Nombre del usuario autenticado. */
    private String nombre;

    /** Rol del usuario autenticado. */
    private String rol;

    /**
     * Constructor para crear una respuesta de autenticación.
     *
     * @param token token JWT generado
     * @param user_id ID del usuario
     * @param nombre nombre del usuario
     * @param rol rol del usuario
     */
    public AuthLoginResponse(String token, int user_id, String nombre, String rol) {
        this.token   = token;
        this.userId = user_id;
        this.nombre  = nombre;
        this.rol     = rol;
    }

    /**
     * Obtiene el token JWT.
     * @return token JWT
     *
     * Nota: No se ha empleado debido a la falta de tiempo para implementar la seguridad completa con JWT, pero se deja preparado para futuras mejoras.
     */
    public String getToken()   { return token; }

    /**
     * Obtiene el ID del usuario.
     * @return ID del usuario
     */
    public int getUserId()     { return userId; }

    /**
     * Obtiene el nombre del usuario.
     * @return nombre del usuario
     */
    public String getNombre()  { return nombre; }

    /**
     * Obtiene el rol del usuario.
     * @return rol del usuario
     */
    public String getRol()     { return rol; }
}
