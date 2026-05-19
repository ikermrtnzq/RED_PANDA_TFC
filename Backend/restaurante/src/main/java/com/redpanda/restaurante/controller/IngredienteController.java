package com.redpanda.restaurante.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redpanda.restaurante.entity.Categoria;
import com.redpanda.restaurante.entity.Ingrediente;
import com.redpanda.restaurante.repository.IngredienteRepository;

@RestController
public class IngredienteController {
    private final IngredienteRepository ingredienteRepository;

    IngredienteController(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @GetMapping("/ingrediente")
    public List<Ingrediente> obtenerCarne(){
        return ingredienteRepository.findAll();
    }
    //Para crear una nueva categoria
    @PostMapping("/ingrediente/create")
    public Ingrediente addIngrediente(@RequestBody Ingrediente nuevoIgrediente) {
        // El repositorio guarda la bebida y nos devuelve la bebida guardada con su ID generado.
        return ingredienteRepository.save(nuevoIgrediente);
    }

    //Para borra un Ingrediente de la carta
    @DeleteMapping("/ingrediente/delete/{id}")
    public String eliminarIngrediente(@PathVariable Long id){
        //Comprobamos si existe un postre con dicho id
        if (ingredienteRepository.existsById(id)) {
            //Si existe lo eliminamos
            ingredienteRepository.deleteById(id);
            return "Ingrediente eliminado correctamente: " + id; 
        }else{
            //Si no existe devolvemos un mensaje de error
            return "Error: no existe o no se ha podido eliminar el ingrediente con id: " + id;
        }
    }
}
