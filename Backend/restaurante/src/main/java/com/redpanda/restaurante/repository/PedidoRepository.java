package com.redpanda.restaurante.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.redpanda.restaurante.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //Query para obtener los pedidos en base a un id de un usuario
    @Query("SELECT p FROM Pedido p WHERE p.usuario.id = :usuarioID")
    List<Pedido> findByUsuarioId(Long usuarioID);
    
}
