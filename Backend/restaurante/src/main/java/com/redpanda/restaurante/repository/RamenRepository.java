package com.redpanda.restaurante.repository;

//Importa la interfaz que permite que Spring genere automáticamente consultas SQL
import org.springframework.data.jpa.repository.JpaRepository;

//Importa la entidad Ramen para que el repositorio sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Ramen;

public interface RamenRepository extends JpaRepository<Ramen, Long> {

}
