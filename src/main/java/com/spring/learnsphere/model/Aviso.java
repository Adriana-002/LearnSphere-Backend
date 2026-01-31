package com.spring.learnsphere.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "avisos")
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aviso_id", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "mensaje", nullable = false, length = Integer.MAX_VALUE)
    private String mensaje;

    @ColumnDefault("false")
    @Column(name = "es_importante")
    private Boolean esImportante;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_publicacion")
    private Instant fechaPublicacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getEsImportante() {
        return esImportante;
    }

    public void setEsImportante(Boolean esImportante) {
        this.esImportante = esImportante;
    }

    public Instant getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Instant fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

}