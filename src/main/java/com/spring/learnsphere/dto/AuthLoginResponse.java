package com.spring.learnsphere.dto;

public class AuthLoginResponse {
    private String token;
    private int user_id;
    private String nombre;
    private String rol;

    public AuthLoginResponse(String token, int user_id, String nombre, String rol) {
        this.token   = token;
        this.user_id = user_id;
        this.nombre  = nombre;
        this.rol     = rol;
    }

    public String getToken()   { return token; }
    public int getUserId()     { return user_id; }
    public String getNombre()  { return nombre; }
    public String getRol()     { return rol; }
}
