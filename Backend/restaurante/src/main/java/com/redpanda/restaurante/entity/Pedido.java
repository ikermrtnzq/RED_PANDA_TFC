package com.redpanda.restaurante.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
//Nombre de la tabla
@Table(name = "pedido")
public class Pedido {

    //Clave primaria
    @Id
    //Se genera automaticamente(AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos de la tabla
    private double precio;

    //Relacion 1 a N 
    //Varios pedidos pertenecen a UN usuario
    //Es la clave foranea FK
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    //Relacion 1 a N
    //Un pedido tiene varias lineas de pedido
    //mappedBy indica que la relacion esta mapeada por el atributo "pedido" en la clase LineaPedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonManagedReference // Para que no entre en un bucle infinito
    private List<LineaPedido> lineasPedido;


    //Getters
    public Long getId() {
        return id;
    }
    public double getPrecio() {
        return precio;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public List<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    //Setters
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setLineasPedido(List<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }


}