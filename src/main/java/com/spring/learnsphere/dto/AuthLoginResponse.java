package com.spring.learnsphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthLoginResponse {
    private String token;
    @JsonProperty("user_id")
    private Integer userId;
    private String nombre;
    private String rol;

    public AuthLoginResponse(String token, int user_id, String nombre, String rol) {
        this.token   = token;
        this.userId = user_id;
        this.nombre  = nombre;
        this.rol     = rol;
    }

    public String getToken()   { return token; }
    public int getUserId()     { return userId; }
    public String getNombre()  { return nombre; }
    public String getRol()     { return rol; }
}
