package com.velazquez.apirestpi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.config.MainSecurityConfiguration;
import com.velazquez.apirestpi.dto.CredencialesDTO;
import com.velazquez.apirestpi.dto.JwtDTO;
import com.velazquez.apirestpi.models.Consumidor;
import com.velazquez.apirestpi.models.Ofertante;
import com.velazquez.apirestpi.services.impl.ConsumidorServiceImpl;
import com.velazquez.apirestpi.services.impl.CredentialsServiceImpl;
import com.velazquez.apirestpi.services.impl.OfertanteServiceImpl;
import com.velazquez.apirestpi.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AutController {

    @Autowired
    private CredentialsServiceImpl credsService;

    @Autowired
    private OfertanteServiceImpl ofertanteService;

    @Autowired
    private ConsumidorServiceImpl consumidorService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/registroOf")
    public ResponseEntity<?> registroOfertante(@RequestBody Ofertante ofertante) {
        Optional<Ofertante> ofertanteBd = ofertanteService.getOfertanteById(ofertante.getId());

        if (ofertanteBd.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (ofertante.getUsuario().getId() == -1) {
                ofertante.getUsuario().setContrasenya(MainSecurityConfiguration.getPasswordEncoder().encode(ofertante.getUsuario().getContrasenya()));
                usuarioService.insertUsuario(ofertante.getUsuario());
                ofertanteService.insertOfertante(ofertante);
            } else {
                ofertanteService.insertOfertante(ofertante);
            }
            return new ResponseEntity<>(ofertante, HttpStatus.CREATED);
        }
    }

    @PostMapping("/registroCons")
    public ResponseEntity<?> registroConsumidor(@RequestBody Consumidor consumidor) {
        
        Optional<Consumidor> consumidorBd = consumidorService.getConsumidorById(consumidor.getId());

        if (consumidorBd.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (consumidor.getUsuario().getId() == -1) {
                consumidor.getUsuario().setContrasenya(MainSecurityConfiguration.getPasswordEncoder().encode(consumidor.getUsuario().getContrasenya()));
                usuarioService.insertUsuario(consumidor.getUsuario());
                consumidorService.insertConsumidor(consumidor);
            } else {
                consumidorService.insertConsumidor(consumidor);
            }
            return new ResponseEntity<>(consumidor ,HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredencialesDTO creds) {
        String jwt = credsService.crearJWTLogin(creds);
        if (jwt != "") {
            return new ResponseEntity<>(new JwtDTO(jwt), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new JwtDTO(jwt), HttpStatus.NOT_FOUND);
        }
    }
}
