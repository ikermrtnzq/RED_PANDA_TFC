package com.redpanda.restaurante.controller;

//Importa la entidad Carne para que el controlador sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Categoria;

//Importa el repositorio de Carne para que el controlador pueda acceder a los datos de la tabla correspondiente
import com.redpanda.restaurante.repository.CategoriaRepository;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categoria")
    public List<Categoria> obtenerCarne(){
        return categoriaRepository.findAll();
    }

    //Para crear una nueva categoria
    @PostMapping("/categoria/create")
    public Categoria addCat(@RequestBody Categoria nuevaCategoria) {
        // El repositorio guarda la bebida y nos devuelve la bebida guardada con su ID generado.
        return categoriaRepository.save(nuevaCategoria);
    }

    //Para borra una categoria de la carta
    @DeleteMapping("/categoria/delete/{id}")
    public String eliminarCat(@PathVariable Long id){
        //Comprobamos si existe un postre con dicho id
        if (categoriaRepository.existsById(id)) {
            //Si existe lo eliminamos
            categoriaRepository.deleteById(id);
            return "Categoria eliminada correctamente: " + id; 
        }else{
            //Si no existe devolvemos un mensaje de error
            return "Error: no existe o no se ha podido eliminar la categoria con id: " + id;
        }
    }

    
}