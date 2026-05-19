package com.redpanda.restaurante.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redpanda.restaurante.entity.LineaPedido;
import com.redpanda.restaurante.repository.LineaPedidoRepository;

@RestController
public class LineaPedidoController {

    private final LineaPedidoRepository lineaPedidoRepository;

    LineaPedidoController(LineaPedidoRepository lineaPedidoRepository) {
        this.lineaPedidoRepository = lineaPedidoRepository;
    }

    //Crear una nueva linea de pedido
    //Se recibe un objeto LineaPedido en el cuerpo de la solicitud y se guarda en la base de datos
    //Se devuelve la linea de pedido creada con su id generado
    @PostMapping("/lineaPedido/create")
    public LineaPedido createLineaPedido(@RequestBody LineaPedido lineaPedido) {
        LineaPedido lineaPedido2 = lineaPedidoRepository.save(lineaPedido);
        return lineaPedido2;
    }


}