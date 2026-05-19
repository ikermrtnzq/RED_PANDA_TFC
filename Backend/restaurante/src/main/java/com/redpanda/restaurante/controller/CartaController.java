package com.redpanda.restaurante.controller;

import java.util.ArrayList;
//Importa la entidad Carta para que el controlador sepa a qué tabla de la base de datos se refiere
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redpanda.restaurante.ElementoCarta;
import com.redpanda.restaurante.entity.Carta;
import com.redpanda.restaurante.repository.CartaRepository;


@RestController
public class CartaController {

    private final CartaRepository cartaRepository;

    CartaController(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }

    @GetMapping("/carta")
    public List<ElementoCarta> obteneCarta(@RequestParam(defaultValue = "0") int offset) {
        Carta carta = cartaRepository.findAll().get(0);
        List<ElementoCarta> cartaPlana = new ArrayList<>();

        carta.getSushi().forEach(sushi -> 
            cartaPlana.add(new ElementoCarta(sushi.getId(), sushi.getNombre(), sushi.getDescripcion(), sushi.getPrecio(), sushi.getImagen(), "sushi", 1, sushi.getCantidad()))
        );

        carta.getRamen().forEach(ramen -> 
            cartaPlana.add(new ElementoCarta(ramen.getId(), ramen.getNombre(), ramen.getDescripcion(), ramen.getPrecio(), ramen.getImagen(), "ramen", 1, 1))
        );

        carta.getBebidas().forEach(bebida -> 
            cartaPlana.add(new ElementoCarta(bebida.getId(), bebida.getNombre(), bebida.getDescripcion(), bebida.getPrecio(), bebida.getImagen(), "bebida", 1, 1))
        );

        carta.getEntrantes().forEach(entrante -> 
            cartaPlana.add(new ElementoCarta(entrante.getId(), entrante.getNombre(), entrante.getDescripcion(), entrante.getPrecio(), entrante.getImagen(), "entrante", 1, entrante.getCantidad()))
        );

        carta.getPostres().forEach(postre -> 
            cartaPlana.add(new ElementoCarta(postre.getId(), postre.getNombre(), postre.getDescripcion(), postre.getPrecio(), postre.getImagen(), "postre", 1, 1))
        );

        carta.getCarne().forEach(carne -> 
            cartaPlana.add(new ElementoCarta(carne.getId(), carne.getNombre(), carne.getDescripcion(), carne.getPrecio(), carne.getImagen(), "carne", 1, 1))
        );

        carta.getPescado().forEach(pescado -> 
            cartaPlana.add(new ElementoCarta(pescado.getId(), pescado.getNombre(), pescado.getDescripcion(), pescado.getPrecio(), pescado.getImagen(), "pescado", 1, 1))
        );

        //Cada carga es de 8 en 8
        int numPlatos = 8;
        int totalPlatos = cartaPlana.size();

        //Para saber si hemos llegado al final y no pasarnos de numero
        if (offset >= totalPlatos) {
            return new ArrayList<>();  
        }

        int fin = Math.min(offset + numPlatos, totalPlatos);

        return cartaPlana.subList(offset, fin);
    }

    //Para obtener la carta entera sin paginar, se usa para el admin
    @GetMapping("/cartaCompleta")
    public List<ElementoCarta> obteneCartaCompleta() {
        Carta carta = cartaRepository.findAll().get(0);
        List<ElementoCarta> cartaPlana = new ArrayList<>();

        carta.getSushi().forEach(sushi -> 
            cartaPlana.add(new ElementoCarta(sushi.getId(), sushi.getNombre(), sushi.getDescripcion(), sushi.getPrecio(), sushi.getImagen(), "sushi", 1, sushi.getCantidad()))
        );

        carta.getRamen().forEach(ramen -> 
            cartaPlana.add(new ElementoCarta(ramen.getId(), ramen.getNombre(), ramen.getDescripcion(), ramen.getPrecio(), ramen.getImagen(), "ramen", 1, 1))
        );
        carta.getBebidas().forEach(bebida -> 
            cartaPlana.add(new ElementoCarta(bebida.getId(), bebida.getNombre(), bebida.getDescripcion(), bebida.getPrecio(), bebida.getImagen(), "bebida", 1, 1))
        );
        carta.getEntrantes().forEach(entrante -> 
            cartaPlana.add(new ElementoCarta(entrante.getId(), entrante.getNombre(), entrante.getDescripcion(), entrante.getPrecio(), entrante.getImagen(), "entrante", 1, entrante.getCantidad()))
        );
        carta.getPostres().forEach(postre -> 
            cartaPlana.add(new ElementoCarta(postre.getId(), postre.getNombre(), postre.getDescripcion(), postre.getPrecio(), postre.getImagen(), "postre", 1, 1))
        );
        carta.getCarne().forEach(carne -> 
            cartaPlana.add(new ElementoCarta(carne.getId(), carne.getNombre(), carne.getDescripcion(), carne.getPrecio(), carne.getImagen(), "carne", 1, 1))
        );
        carta.getPescado().forEach(pescado -> 
            cartaPlana.add(new ElementoCarta(pescado.getId(), pescado.getNombre(), pescado.getDescripcion(), pescado.getPrecio(), pescado.getImagen(), "pescado", 1, 1))
        );
        return cartaPlana;
    }
}
