package com.redpanda.restaurante.controller;

import com.redpanda.restaurante.entity.Ingrediente;
//Importa la entidad Postre para que el controlador sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Postre;
//Importa el repositorio de Postre para que el controlador pueda acceder a los datos de la tabla correspondiente
import com.redpanda.restaurante.repository.PostreRepository;

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
public class PostreController {

    private final PostreRepository postreRepository;

    PostreController(PostreRepository postreRepository) {
        this.postreRepository = postreRepository;
    }

    @GetMapping("/postre")
    public List<Postre> obtenerPostre(){
        return postreRepository.findAll();
    }

    //Para crear un nuevo postre y añadirlo a la carta
    @PostMapping("/postre/create")
    public Postre addPostre(@RequestBody Postre nuevoPostre) {
        // El repositorio guarda la bebida y nos devuelve la bebida guardada con su ID generado.
        return postreRepository.save(nuevoPostre);
    }

    //Para borra un postre de la carta
    @DeleteMapping("/postre/delete/{id}")
    public String eliminarPostre(@PathVariable Long id){
        //Comprobamos si existe un postre con dicho id
        if (postreRepository.existsById(id)) {
            //Si existe lo eliminamos
            postreRepository.deleteById(id);
            return "Postre eliminado correctamente: " + id; 
        }else{
            //Si no existe devolvemos un mensaje de error
            return "Error: no existe o no se ha podido eliminar el postre con id: " + id;
        }
    }

      //Para actualizar un postre de la carta 
    @PutMapping("/postre/update/{id}")
    public Postre actualizarPostre(@PathVariable Long id, @RequestBody Postre postreActualizado) {
        if (postreRepository.existsById(id)) {
            postreActualizado.setId(id);
            return postreRepository.save(postreActualizado);
        } else {
            throw new RuntimeException("Postre no encontrado con id: " + id);
        }
    }

    //obtener sus ingredientes
    @GetMapping("/postre/ingredientes/{id}")
    public List<Ingrediente> obtenerIngredientesPostre(@PathVariable Long id) {
        if (postreRepository.existsById(id)) {
            Postre postre = postreRepository.findById(id).orElseThrow(() -> new RuntimeException("Postre no encontrado con id: " + id));
            return postre.getIngredientes();
        } 
        return null; 
    }

    
}