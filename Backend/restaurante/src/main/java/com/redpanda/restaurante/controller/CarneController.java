package com.redpanda.restaurante.controller;

//Importa la entidad Carne para que el controlador sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Carne;
import com.redpanda.restaurante.entity.Ingrediente;
//Importa el repositorio de Carne para que el controlador pueda acceder a los datos de la tabla correspondiente
import com.redpanda.restaurante.repository.CarneRepository;

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
public class CarneController {

    private final CarneRepository carneRepository;

    CarneController(CarneRepository carneRepository) {
        this.carneRepository = carneRepository;
    }

    @GetMapping("/carne")
    public List<Carne> obtenerCarne(){
        return carneRepository.findAll();
    }

    //Para crear una nueva bebida y añadirlo a la carta
    @PostMapping("/carne/create")
    public Carne addCarne(@RequestBody Carne nuevaCarne) {
        // El repositorio guarda la bebida y nos devuelve la carne guardada con su ID generado.
        return carneRepository.save(nuevaCarne);
    }

    //Para borra una carne de la carta
    @DeleteMapping("/carne/delete/{id}")
    public String eliminarCarne(@PathVariable Long id){
        //Comprobamos si existe una carne con dicho id
        if (carneRepository.existsById(id)) {
            //Si existe lo eliminamos
            carneRepository.deleteById(id);
            return "Carne eliminada correctamente: " + id; 
        }else{
            //Si no existe devolvemos un mensaje de error
            return "Error: no existe o no se ha podido eliminar la carne con id: " + id;
        }
    }

    //Para actualizar una carne de la carta 
    @PutMapping("/carne/update/{id}")
    public Carne actualizarCarne(@PathVariable Long id, @RequestBody Carne carneActualizada) {
        if (carneRepository.existsById(id)) {
            carneActualizada.setId(id);
            return carneRepository.save(carneActualizada);
        } 
        return null;
    }

    //obtener sus ingredientes
    @GetMapping("/carne/ingredientes/{id}")
    public List<Ingrediente> obtenerIngredientesCarne(@PathVariable Long id) {
        if (carneRepository.existsById(id)) {
            Carne carne = carneRepository.findById(id).orElseThrow(() -> new RuntimeException("Carne no encontrada con id: " + id));
            return carne.getIngredientes();
        }
        return null;
    }

   
}