package com.velazquez.apirestpi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.models.Usuario;
import com.velazquez.apirestpi.services.impl.UsuarioServiceImpl;



@RestController
@RequestMapping("/usuario") 
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("getUsuarioUsername/{username}")
    public ResponseEntity<Usuario> getUsuarioByUsername(@PathVariable String username) {
        Optional<Usuario> usuarioGet = usuarioService.getUsuarioByUsername(username);

        if(usuarioGet.isPresent()){
            return new ResponseEntity<>(usuarioGet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    

    @GetMapping("getUsuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        if(usuarioService.getUsuarioById(id).isPresent()){
            Usuario usuarioGet = usuarioService.getUsuarioById(id).get();
            return new ResponseEntity<>(usuarioGet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
    

     @GetMapping("/allUsuarios")
     public ResponseEntity<?> allUsuarios() {
         return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
     }
}
