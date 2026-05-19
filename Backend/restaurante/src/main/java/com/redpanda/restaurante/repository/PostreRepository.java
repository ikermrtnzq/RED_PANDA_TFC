package com.redpanda.restaurante.repository;

//Importa la interfaz que permite que Spring genere automáticamente consultas SQL
import org.springframework.data.jpa.repository.JpaRepository;
//Importa la entidad Postre para que el repositorio sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Postre;

public interface PostreRepository extends JpaRepository<Postre, Long> {

}
