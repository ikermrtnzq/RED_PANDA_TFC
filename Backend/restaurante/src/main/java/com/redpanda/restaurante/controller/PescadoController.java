package com.redpanda.restaurante.controller;

import com.redpanda.restaurante.entity.Ingrediente;
//Importa la entidad Pescado para que el controlador sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Pescado;
//Importa el repositorio de Pescado para que el controlador pueda acceder a los datos de la tabla correspondiente
import com.redpanda.restaurante.repository.PescadoRepository;

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
public class PescadoController {

    private final PescadoRepository pescadoRepository;

    PescadoController(PescadoRepository pescadoRepository) {
        this.pescadoRepository = pescadoRepository;
    }

    @GetMapping("/pescado")
    public List<Pescado> obtenerPescado(){
        return pescadoRepository.findAll();
    }

    //Para crear un nuevo Pescado y añadirlo a la carta
    @PostMapping("/pescado/create")
    public Pescado addPescado(@RequestBody Pescado nuevoPescado) {
        // El repositorio guarda la bebida y nos devuelve la bebida guardada con su ID generado.
        return pescadoRepository.save(nuevoPescado);
    }

    //Para borra un pescado de la carta
    @DeleteMapping("/pescado/delete/{id}")
    public String eliminarPescado(@PathVariable Long id){
        //Comprobamos si existe un pescado con dicho id
        if (pescadoRepository.existsById(id)) {
            //Si existe lo eliminamos
            pescadoRepository.deleteById(id);
            return "Pescado eliminado correctamente: " + id; 
        }else{
            //Si no existe devolvemos un mensaje de error
            return "Error: no existe o no se ha podido eliminar el pescado con id: " + id;
        }
    }

    //Para actualizar un pescado
    @PutMapping("/pescado/update/{id}")
    public Pescado actualizarPescado(@PathVariable Long id, @RequestBody Pescado pescadoActualizado) {
        if (pescadoRepository.existsById(id)) {
            pescadoActualizado.setId(id);
            return pescadoRepository.save(pescadoActualizado);
        } else {
            throw new RuntimeException("Pescado no encontrado con id: " + id);
        }
    }

    //obtener sus ingredientes
    @GetMapping("/pescado/ingredientes/{id}")
    public List<Ingrediente> obtenerIngredientesPescado(@PathVariable Long id) {
        if (pescadoRepository.existsById(id)) {
            Pescado pescado = pescadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pescado no encontrado con id: " + id));
            return pescado.getIngredientes();
        }
        return null;
    }

    
}