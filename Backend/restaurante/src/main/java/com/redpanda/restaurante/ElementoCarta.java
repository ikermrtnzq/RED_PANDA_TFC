package com.redpanda.restaurante;

//Clase que sirve para mandar los elementos de la carta formateados para su uso en el Frontend
public class ElementoCarta {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private String categoria_slug;
    private int categoria_id;
    private Integer cantidad;

    public ElementoCarta(Long id, String nombre, String descripcion, Double precio, String imagen, String slug, int catId, Integer cantidad ){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria_slug = slug;
        this.categoria_id = catId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }   
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getCategoria_slug() {
        return categoria_slug;
    }
    public void setCategoria_slug(String categoria_slug) {
        this.categoria_slug = categoria_slug;
    }
    public int getCategoria_id() {
        return categoria_id;
    }
    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
