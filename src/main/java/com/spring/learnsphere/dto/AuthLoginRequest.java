package com.spring.learnsphere.dto;

/**
 * DTO que representa la solicitud de autenticación para login.
 *
 * Este DTO contiene las credenciales del usuario necesarias para
 * autenticarse en el sistema.
 *
 * @author Adriana
 */
public class AuthLoginRequest {
    /** Correo electrónico del usuario. */
    private String email;

    /** Contraseña del usuario. */
    private String password;

    /**
     * Obtiene el correo electrónico.
     * @return correo electrónico
     */
    public String getEmail()    { return email; }

    /**
     * Obtiene la contraseña.
     * @return contraseña
     */
    public String getPassword() { return password; }
}
