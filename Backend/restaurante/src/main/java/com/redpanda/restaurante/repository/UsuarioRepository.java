package com.redpanda.restaurante.repository;

import java.util.Optional;

//Importa la interfaz que permite que Spring genere automáticamente consultas SQL
import org.springframework.data.jpa.repository.JpaRepository;
//Importa la entidad Sushi para que el repositorio sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
