package com.spring.learnsphere.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un material escolar en el sistema LearnSphere.
 *
 * Esta clase mapea la tabla "material_escolar" de la base de datos y contiene
 * la información de libros de texto, materiales educativos y recursos necesarios
 * para los cursos y asignaturas.
 *
 * @author Adriana
 */
@Entity
@Table(name = "material_escolar")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class MaterialEscolar {
    /**
     * Identificador único del material escolar.
     * Se genera automáticamente mediante auto-incremento en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Integer id;

    /**
     * Nombre del material escolar.
     * Campo obligatorio con una longitud máxima de 200 caracteres.
     * Ejemplo: Libro de Matemáticas, Cuaderno de Laboratorio, etc.
     */
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    /**
     * Editorial que publica el material.
     * Campo opcional con una longitud máxima de 200 caracteres.
     * Aplicable especialmente para libros de texto.
     */
    @Column(name = "editorial", length = 200)
    private String editorial;

    /**
     * Número ISBN del material.
     * Campo opcional con una longitud máxima de 20 caracteres.
     * ISBN para identificar unívocamente libros publicados.
     */
    @Column(name = "isbn", length = 20)
    private String isbn;

}