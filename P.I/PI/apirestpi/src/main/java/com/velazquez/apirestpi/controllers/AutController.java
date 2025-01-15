package com.velazquez.apirestpi.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.velazquez.apirestpi.models.Usuario;
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

    private final Logger log = LoggerFactory.getLogger(AutController.class);

    @PostMapping("/registroOf")
    public ResponseEntity<?> registroOfertante(@RequestBody Ofertante ofertante) {
        log.debug(ofertante.toString());
        Optional<Ofertante> ofertanteBd = ofertanteService.getOfertanteById(ofertante.getId());

        if (ofertanteBd.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (ofertante.getUsuario().getId() == -1) {
                ofertante.getUsuario().setContrasenya(
                        MainSecurityConfiguration.getPasswordEncoder().encode(ofertante.getUsuario().getContrasenya()));
                ofertante.setUsuario(usuarioService.insertUsuario(ofertante.getUsuario()));
                ofertanteService.insertOfertante(ofertante);
            } else {
                ofertanteService.insertOfertante(ofertante);
            }
            return new ResponseEntity<>(ofertante, HttpStatus.CREATED);
        }
    }

    @PostMapping("/registroCons")
    public ResponseEntity<?> registroConsumidor(@RequestBody Consumidor consumidor) {

        Optional<Usuario> usuarioBd = usuarioService.getUsuarioByUsername(consumidor.getUsuario().getUsername());
        consumidorService.getConsumidorById(consumidor.getId());
        if (usuarioBd.isPresent()) {
            consumidorService.insertConsumidor(consumidor);
        } else {
            consumidor.getUsuario().setContrasenya(
                    MainSecurityConfiguration.getPasswordEncoder().encode(consumidor.getUsuario().getContrasenya()));
            consumidor.setUsuario(usuarioService.insertUsuario(consumidor.getUsuario()));
            consumidorService.insertConsumidor(consumidor);
        }
        return new ResponseEntity<>(consumidor, HttpStatus.CREATED);
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
