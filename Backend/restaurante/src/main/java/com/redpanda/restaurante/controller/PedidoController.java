package com.redpanda.restaurante.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.redpanda.restaurante.entity.LineaPedido;
import com.redpanda.restaurante.entity.Pedido;
import com.redpanda.restaurante.repository.PedidoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;



@RestController
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    //Obtener todos los pedidos
    @GetMapping("/pedidos")
    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    //Obtener pedidos por id de usuario
    //Se usa Optional para manejar el caso en el que el usuario no tenga pedidos o no exista
    @GetMapping("/pedidos/{userId}")
    public List<Pedido> getPedidosByUsuarioId(@PathVariable Long userId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(userId);
        //Añadimos las lineas de pedido a cada pedido para que se devuelvan en la respuesta
        return pedidos;
    }

    //Crear un nuevo pedido
   @PostMapping("/pedido/create")
    public Pedido createPedido(@RequestBody Pedido pedido) {
        if (pedido.getLineasPedido() != null) {
            for (LineaPedido linea : pedido.getLineasPedido()) {
                linea.setPedido(pedido); 
            }
        }
        return pedidoRepository.save(pedido);
    }


}