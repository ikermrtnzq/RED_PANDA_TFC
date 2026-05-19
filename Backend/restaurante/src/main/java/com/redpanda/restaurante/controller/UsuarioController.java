package com.redpanda.restaurante.controller;

//Importa la entidad Usuario para que el controlador sepa a qué tabla de la base de datos se refiere
import com.redpanda.restaurante.entity.Usuario;

//Importa el repositorio de usuarios para que el controlador pueda acceder a los datos de la tabla correspondiente
import com.redpanda.restaurante.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
public class UsuarioController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    UsuarioController(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
    }

    //Para iniciar Sesion nada mas entrar en la web
    //En el body se enviará un JSON con el email y la contraseña del usuario
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Usuario datosLogin, HttpSession sesion) {
        System.out.println("Hash para 1234: " + passwordEncoder.encode("1234"));
        System.out.println("Hash para abcd: " + passwordEncoder.encode("abcd"));
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(datosLogin.getEmail());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            //Utilizamos en codificador para poder saber si la contraseña coincide o no
           if (passwordEncoder.matches(datosLogin.getPwd(), usuario.getPwd())) {
                sesion.setAttribute("usuario", usuario);
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(401).body("Contraseña incorrecta");
            }
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        
    }

    //Registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario nuevoUsuario) {
       if (usuarioRepository.findByEmail(nuevoUsuario.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("El usuario ya existe"); // Usuario ya existe
        }

        //Ciframos la contraseña
        nuevoUsuario.setPwd(passwordEncoder.encode(nuevoUsuario.getPwd()));
        usuarioRepository.save(nuevoUsuario);
        
        
        return ResponseEntity.ok().body("{\"mensaje\": \"Usuario creado correctamente\"}");
        
    }


    //Cierra sesion
    @PostMapping("/logout")
    public ResponseEntity<String> cerrarSesion(HttpSession sesion) {
        sesion.invalidate();
        return ResponseEntity.ok("Sesión cerrada");
    }
    
    

    

}