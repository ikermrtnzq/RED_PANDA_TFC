package com.redpanda.restaurante.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
//Nombre de la tabla
@Table(name = "entrante")
public class Entrante {

    //Clave primaria
    @Id
    //Se genera automaticamente(AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos de la tabla
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;
    private Integer cantidad;

    //Relacion 1 a N
    //Varios entrantes estan en la carta
    //Clave foranea FK
    @ManyToOne
    @JoinColumn(name = "id_carta")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Carta carta;

    //Relacion N a M
    //Un entrante puede tener varios ingredientes y un ingrediente puede estar en varios entrantes
    @ManyToMany
    @JoinTable(
        name = "entrante_ingrediente",
        joinColumns = @JoinColumn(name = "id_entrante"),
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
    public String getDescripcion() {
        return descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public String getImagen() {
        return imagen;
    }
    public Integer getCantidad() {
        return cantidad;
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
        return "entrante"; 
    }
    


}