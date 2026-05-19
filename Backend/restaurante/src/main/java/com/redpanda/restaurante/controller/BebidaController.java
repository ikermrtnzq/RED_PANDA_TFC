package com.redpanda.restaurante.controller;

//Importa la entidad Bebida para que el controlador sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Bebida;
//Importa el repositorio de Bebida para que el controlador pueda acceder a los datos de la tabla correspondiente
import com.redpanda.restaurante.repository.BebidaRepository;

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
public class BebidaController {

    private final  BebidaRepository bebidaRepository;

    BebidaController(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    @GetMapping("/bebida")
    public List<Bebida> obtenerBebida(){
        return bebidaRepository.findAll();
    }

    //Para crear una nueva bebida y añadirlo a la carta
    @PostMapping("/bebida/create")
    public Bebida addRamen(@RequestBody Bebida nuevBebida) {
        // El repositorio guarda la bebida y nos devuelve la bebida guardada con su ID generado.
        return bebidaRepository.save(nuevBebida);
    }

    //Para borra una bebida de la carta
    @DeleteMapping("/bebida/delete/{id}")
    public String eliminarBebida(@PathVariable Long id){
        //Comprobamos si existe un bebida con dicho id
        if (bebidaRepository.existsById(id)) {
            //Si existe lo eliminamos
            bebidaRepository.deleteById(id);
            return "Bebida eliminada correctamente: " + id; 
        }else{
            //Si no existe devolvemos un mensaje de error
            return "Error: no existe o no se ha podido eliminar la bebida con id: " + id;
        }
    }

    //Para actualizar una bebida de la carta 
    @PutMapping("/bebida/update/{id}")
    public Bebida actualizarBebida(@PathVariable Long id, @RequestBody Bebida bebidaActualizada) {
        if (bebidaRepository.existsById(id)) {
            bebidaActualizada.setId(id);
            return bebidaRepository.save(bebidaActualizada);
        } else {
            throw new RuntimeException("Bebida no encontrada con id: " + id);
        }
    }

    
}