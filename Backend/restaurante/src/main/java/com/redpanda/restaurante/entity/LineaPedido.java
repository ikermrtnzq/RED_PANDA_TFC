package com.redpanda.restaurante.entity;


import java.util.List;

import jakarta.persistence.*;

@Entity
//Nombre de la tabla
@Table(name = "linea_pedido")
public class LineaPedido {

    //ID que se genera automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos de la tabla
    private int cantidad;
    private double precio;
    private String nombrePlato;
    private String categoriaPlato;
    private String ingredientesQuitados; ;
    private String ingredientesIDs;

    //Relación con Pedido
    //Una linea de pedido pertenece a un pedido
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    @com.fasterxml.jackson.annotation.JsonBackReference // Para que no entre en un bucle infinito
    private Pedido pedido;



    //Getters
    public Long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
    public String getNombrePlato() {
        return nombrePlato;
    }
    public String getCategoriaPlato() {
        return categoriaPlato;
    }
    public String getIngredientesQuitados() {
        return ingredientesQuitados;
    }
    public String getIngredientesIDs() {
        return ingredientesIDs;
    }
    public Pedido getPedido() {
        return pedido;
    }

    //Setters
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }
    public void setCategoriaPlato(String categoriaPlato) {
        this.categoriaPlato = categoriaPlato;
    }
    public void setIngredientesQuitados(String ingredientesQuitados) {
        this.ingredientesQuitados = ingredientesQuitados;
    }
    public void setIngredientesIDs(String ingredientesIDs) {
        this.ingredientesIDs = ingredientesIDs;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    
}
