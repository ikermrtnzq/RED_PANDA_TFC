package com.redpanda.restaurante.controller;

import com.redpanda.restaurante.entity.Ingrediente;
//Importa la entidad Sushi para que el controlador sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Sushi;

//Importa el repositorio de Sushi para que el controlador pueda acceder a los datos de la tabla correspondiente
import com.redpanda.restaurante.repository.SushiRepository;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SushiController {

    private final SushiRepository sushiRepository;

    SushiController(SushiRepository sushiRepository) {
        this.sushiRepository = sushiRepository;
    }


    @GetMapping("/sushi")
    public List<Sushi> obtenerSushi(){
        return sushiRepository.findAll();
    }

    //Para crear un nuevo sushi y añadirlo a la carta
    @PostMapping("/sushi/create")
    public Sushi addSushi(@RequestBody Sushi nuevoSushi) {
        // El repositorio guarda el sushi y nos devuelve el sushi guardado con su ID generado.
        return sushiRepository.save(nuevoSushi);
    }

    //Para borra un sushi de la carta
    @DeleteMapping("/sushi/delete/{id}")
    public String eliminarSushi(@PathVariable Long id){
        //Comprobamos si existe un sushi con dicho id
        if (sushiRepository.existsById(id)) {
            //Si existe lo eliminamos
            sushiRepository.deleteById(id);
            return "Sushi eliminado correctamente: " + id; 
        }else{
            //Si no existe devolvemos un mensaje de error
            return "Error: no existe o no se ha podido eliminar el sushi con id: " + id;
        }
    }

    //Para actualizar un sushi 
    @PutMapping("/sushi/update/{id}")
    public Sushi actualizarSushi(@PathVariable Long id, @RequestBody Sushi sushiActualizado) {
        if (sushiRepository.existsById(id)) {
            sushiActualizado.setId(id);
            return sushiRepository.save(sushiActualizado);
        } else {
            throw new RuntimeException("Sushi no encontrado con id: " + id);
        }
    }

    //Obtener sus ingredientes
    @GetMapping("/sushi/ingredientes/{id}")
    public List<Ingrediente> obtenerIngredientes(@PathVariable Long id) {
        if (sushiRepository.existsById(id)) {
            Sushi sushi = sushiRepository.findById(id).orElseThrow(); new RuntimeErrorException( null, "Sushi no encontrado con id: " + id);
            return sushi.getIngredientes();
            
        }
        return null;
    
    }
}