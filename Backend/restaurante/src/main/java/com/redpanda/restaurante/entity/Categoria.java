package com.redpanda.restaurante.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
//Nombre de la tabla
@Table(name = "categoria")
public class Categoria {

    //Clave primaria PK
    @Id
    //Se genera automaticamente (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos de la tabla
    private String nombre;
    private String slug;

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getSlug() {
        return slug;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
}