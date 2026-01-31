package com.spring.learnsphere.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "\"año_academico\"", nullable = false, length = 9)
    private String añoAcademico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAñoAcademico() {
        return añoAcademico;
    }

    public void setAñoAcademico(String añoAcademico) {
        this.añoAcademico = añoAcademico;
    }

}