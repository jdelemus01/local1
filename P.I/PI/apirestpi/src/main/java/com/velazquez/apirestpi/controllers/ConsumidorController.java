package com.velazquez.apirestpi.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.velazquez.apirestpi.models.Consumidor;
import com.velazquez.apirestpi.models.Usuario;
import com.velazquez.apirestpi.services.impl.ConsumidorServiceImpl;
import com.velazquez.apirestpi.services.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/consumidor")
public class ConsumidorController {

    @Autowired
    ConsumidorServiceImpl consumidorService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("getConsUser/{username}")
    public ResponseEntity<Consumidor> getConsumidorByUsername(@PathVariable Long username) {
        Optional<Consumidor> consumidorGet = consumidorService.getConsumidorByUsuario(username);

        if(consumidorGet.isPresent()){
            return new ResponseEntity<>(consumidorGet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/getCons/{id}")
    public ResponseEntity<Consumidor> getConsumidor(@PathVariable Long id) {
        if(consumidorService.getConsumidorById(id).isPresent()){
            return new ResponseEntity<>(consumidorService.getConsumidorById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getConsUsernames")
    public List<String> getConsUsernames() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<Consumidor> consumidores = consumidorService.getAllConsumidores();
        List<String> usernamesCons = new ArrayList<>();
        for (Usuario us : usuarios) {
            for (Consumidor cons : consumidores) {
                if(us.getId() == cons.getUsuario().getId()){
                    usernamesCons.add(us.getUsername());
                }
            }
        }
        return usernamesCons;
    }
    
    @PostMapping("/insCons")
    public ResponseEntity<Consumidor> insertConsumidor(@RequestBody Consumidor consumidorIns) {
        
        Optional<Usuario> usuarioCons = usuarioService.getUsuarioById(consumidorIns.getUsuario().getId());

        if(usuarioCons.isPresent()){
            consumidorIns.setUsuario(consumidorIns.getUsuario());
            return new ResponseEntity<>(consumidorService.insertConsumidor(consumidorIns), HttpStatus.CREATED);
        } else {
            consumidorIns.setUsuario(usuarioService.insertUsuario(consumidorIns.getUsuario()));
            return new ResponseEntity<>(consumidorService.insertConsumidor(consumidorIns), HttpStatus.CREATED);
        }
    }

    @PutMapping("/modificarConsumidor/{id}")
    public ResponseEntity<?> updateConsumidor(@PathVariable Long id, @RequestBody Consumidor consumidor) {
        Optional<Consumidor> consumidorGet = consumidorService.getConsumidorById(id);

        if(!consumidorGet.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            consumidorService.updateConsumidor(consumidor);
            return new ResponseEntity<>(consumidor, HttpStatus.OK);
        } 
    }

    @DeleteMapping("/borrarConsumidor/{id}")
    public ResponseEntity<?> deleteConsumidor(@PathVariable Long id) {
        Optional<Consumidor> consumidorGet = consumidorService.getConsumidorById(id);

        if(!consumidorGet.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            consumidorService.deleteConsumidor(consumidorGet.get());;
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
