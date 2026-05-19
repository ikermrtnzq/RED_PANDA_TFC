package com.redpanda.restaurante.entity;


import jakarta.persistence.*;

@Entity
//Nombre de la tabla
@Table(name = "ingrediente")
public class Ingrediente {

    //Clave primaria
    @Id
    //Se genera automaticamente(AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos de la tabla
    private String nombre;

    //Getters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}