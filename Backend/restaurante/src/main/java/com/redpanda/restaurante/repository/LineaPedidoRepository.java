package com.redpanda.restaurante.repository;
import org.springframework.data.jpa.repository.JpaRepository;
//Importamos la entidad LineaPedido
import com.redpanda.restaurante.entity.LineaPedido;

public interface LineaPedidoRepository  extends JpaRepository<LineaPedido, Long> {

    
} 