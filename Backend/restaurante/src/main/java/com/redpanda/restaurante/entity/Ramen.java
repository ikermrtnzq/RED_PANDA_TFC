package com.redpanda.restaurante.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//Nombre de la tabla
@Table(name = "ramen")
public class Ramen {

    //Clave primaria PK
    @Id
    //Se genera automaticamente(AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos de la tabla
    private String nombre;
    private int cantidad;
    private double precio;
    private String imagen;
    private String descripcion;
    

    //Relacion 1 a N 
    //Varios ramenes pueden estar en una carta
    @ManyToOne
    @JoinColumn(name = "id_carta")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Carta carta;
    
    //Relacion N a M 
    //En un ramen hay varios ingredientes y un ingrediente esta en varios ramenes
    @ManyToMany
    //Creamos la tabla intermedia
    @JoinTable(
        name = "ramen_ingrediente",
        //Primera parte de la clave
        joinColumns = @JoinColumn(name = "id_ramen"),
        //Segunda parte de la clave
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
    public String getImagen() {
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
    public void setCarta(Carta carta) {
        this.carta = carta;
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
    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    //Dato que posé la entidad pero no se encuentra en bbdd
    @Transient
    @JsonProperty("categoria_slug")
    public String getCategoriaSlug() {
        return "ramen"; 
    }

}