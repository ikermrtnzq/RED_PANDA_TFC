package com.redpanda.restaurante.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
//Nombre de la tabla
@Table(name = "carne")
public class Carne {

    //Clave primaria PK
    @Id
    //Se genera automaticamente (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos de la tabla
    private String nombre;
    private int cantidad;
    private double precio;
    private String imagen;
    private String descripcion;

    //Relacion 1 a N
    //Varias carnes se encuentran en la carta
    //Clave foranea FK
    @ManyToOne
    @JoinColumn(name = "id_carta")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Carta carta;

    //Relacion N a M
    //Una carne puede tener varios ingredientes y un ingrediente puede estar en varias carnes
    @ManyToMany
    @JoinTable(
        name = "carne_ingrediente",
        joinColumns = @JoinColumn(name = "id_carne"),
        inverseJoinColumns = @JoinColumn(name = "id_ingrediente")
    )
    private List<Ingrediente> ingredientes;

    //Getters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public String getImagen(){
        return imagen;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Carta getCarta() {
        return carta;
    }
    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setCarta(Carta carta) {
        this.carta = carta;
    }
    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }


    //Dato que posé la entidad pero no se encuentra en bbdd
    @Transient
    @JsonProperty("categoria_slug")
    public String getCategoriaSlug() {
        return "carne"; 
    }

}