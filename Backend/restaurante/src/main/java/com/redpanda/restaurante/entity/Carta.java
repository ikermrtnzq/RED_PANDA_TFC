package com.redpanda.restaurante.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//Nombre de la tabla
@Table(name = "carta")
public class Carta {

    //Clave primaria PK
    @Id
    //Se autogenera solo (AUTO_GENERATED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relacion 1 a N
    //Una carta puede tener muchos sushis
    //Clave foranea FK
    @OneToMany(mappedBy = "carta")
    private List<Sushi> sushi;

    //Relacion 1 a N
    //Una carta puede tener muchos ramenes
    //Clave foranea FK
    @OneToMany(mappedBy = "carta")
    private List<Ramen> ramen;

    //Relacion 1 a N
    //Una carta puede tener muchos entrantes
    //Clave foranea FK
    @OneToMany(mappedBy = "carta")
    private List<Entrante> entrantes;

    //Relacion 1 a N
    //Una carta puede tener muchas bebidas
    //Clave foranea FK
    @OneToMany(mappedBy = "carta")
    private List<Bebida> bebidas;

    //Relacion 1 a N
    //Una carta puede tener muchas carnes
    //Clave foranea FK
    @OneToMany(mappedBy = "carta")
    private List<Carne> carne;

    //Relacion 1 a N
    //Una carta puede tener muchos pescados
    //Clave foranea FK
    @OneToMany(mappedBy = "carta")
    private List<Pescado> pescado;

    //Relacion 1 a N
    //Una carta puede tener muchos postres
    //Clave foranea FK
    @OneToMany(mappedBy = "carta")
    private List<Postre> postres;

    public Long getId() {
        return id;
    }

    public List<Sushi> getSushi() {
        return sushi;
    }

    public List<Ramen> getRamen() {
        return ramen;
    }

    public List<Entrante> getEntrantes() {
        return entrantes;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }

    public List<Carne> getCarne() {
        return carne;
    }

    public List<Pescado> getPescado() {
        return pescado;
    }
    public List<Postre> getPostres() {
        return postres;
    }

}