package com.redpanda.restaurante;

//Clase que sirve para mandar los elementos del carrito  formateados para su uso en el Frontend
public class ElementoCarrito {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private String categoria_slug;
    private String imagen;
    private String listaIngredientesQuitados;
    private int[] listaIngredientesIDs;

    public ElementoCarrito(int id, String nombre, double precio, int cantidad, String categoria, String imagen, String listaIngredientesQuitados, int[] listaIngredientesIDs) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria_slug = categoria;
        this.imagen = imagen;
        this.listaIngredientesQuitados = listaIngredientesQuitados;
        this.listaIngredientesIDs = listaIngredientesIDs;
    }
    
    //Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    public String getListaIngredientesQuitados() {
        return listaIngredientesQuitados;
    }

    public int[] getListaIngredientesIDs() {
        return listaIngredientesIDs;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public String getCategoria_slug() {
        return categoria_slug;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCategoria_slug(String categoria_slug) {
        this.categoria_slug = categoria_slug;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public void setListaIngredientesQuitados(String listaIngredientesQuitados) {
        this.listaIngredientesQuitados = listaIngredientesQuitados;
    }

    public void setListaIngredientesIDs(int[] listaIngredientesIDs) {
        this.listaIngredientesIDs = listaIngredientesIDs;
    }

    
}
