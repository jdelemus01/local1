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
import com.velazquez.apirestpi.dto.UsuarioDTO;
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

    @PostMapping("/registroOf")
    public ResponseEntity<?> registroOfertante(@RequestBody Ofertante ofertante) {
        Optional<Usuario> usuarioBd = usuarioService.getUsuarioByUsername(ofertante.getUsuario().getUsername());

        if (usuarioBd.isPresent()) {
            Optional<Ofertante> ofertanteEmail = ofertanteService.getOfertanteByEmail(ofertante.getEmail());
            if (usuarioBd.get().getOfertante() != null) {
                return new ResponseEntity<>("Ya existe una cuenta de ofertante asociada a este usuario.",
                        HttpStatus.CONFLICT);
            } else if (ofertanteEmail.isPresent()) {
                return new ResponseEntity<>("Ya existe una cuenta de ofertante asociada a este email.",
                        HttpStatus.CONFLICT);
            } else {
                ofertante.setUsuario(usuarioBd.get());
                ofertanteService.insertOfertante(ofertante);
                return new ResponseEntity<>(ofertante, HttpStatus.CREATED);
            }
        } else {
            ofertante.getUsuario().setPassword(
                    MainSecurityConfiguration.getPasswordEncoder().encode(ofertante.getUsuario().getPassword()));
            ofertante.setUsuario(usuarioService.insertUsuario(ofertante.getUsuario()));
            ofertanteService.insertOfertante(ofertante);
            return new ResponseEntity<>(ofertante, HttpStatus.CREATED);
        }
    }

    @PostMapping("/registroCons")
    public ResponseEntity<?> registroConsumidor(@RequestBody Consumidor consumidor) {

        Optional<Usuario> usuarioBd = usuarioService.getUsuarioByUsername(consumidor.getUsuario().getUsername());
        if (usuarioBd.isPresent()) {
            if (usuarioBd.get().getConsumidor() != null) {
                return new ResponseEntity<>("Ya existe una cuenta de consumidor asociada a este nombre de usuario.",
                        HttpStatus.CONFLICT);
            } else if (consumidorService.getConsumidorByEmail(consumidor.getEmail()).isPresent()) {
                return new ResponseEntity<>("Ya existe una cuenta de consumidor asociada a este email.",
                        HttpStatus.CONFLICT);
            } else {
                consumidor.setUsuario(usuarioBd.get());
                consumidorService.insertConsumidor(consumidor);
                return new ResponseEntity<>(consumidor, HttpStatus.CREATED);
            }
        } else {
            consumidor.getUsuario().setPassword(
                    MainSecurityConfiguration.getPasswordEncoder().encode(consumidor.getUsuario().getPassword()));
            consumidor.setUsuario(usuarioService.insertUsuario(consumidor.getUsuario()));
            consumidorService.insertConsumidor(consumidor);
            return new ResponseEntity<>(consumidor, HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredencialesDTO creds) {
        Optional<Usuario> usuarioBd = usuarioService.getUsuarioByUsername(creds.getUsuario());
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if (usuarioBd.isPresent()) {
            if (MainSecurityConfiguration.getPasswordEncoder().matches(creds.getContrasenya(), usuarioBd.get().getPassword())) {

                if (ofertanteService.getOfertanteByUsername(usuarioBd.get().getId()).isPresent()) {
                    usuarioDTO.setOfertante(
                            ofertanteService.getOfertanteByUsername(usuarioBd.get().getId()).get().getId());
                } else {
                    usuarioDTO.setOfertante(Long.valueOf(-1));
                }

                if (consumidorService.getConsumidorByUsername(usuarioBd.get().getId()).isPresent()) {
                    usuarioDTO.setConsumidor(
                            consumidorService.getConsumidorByUsername(usuarioBd.get().getId()).get().getId());
                } else {
                    usuarioDTO.setConsumidor(Long.valueOf(-1));
                }

                usuarioDTO.setId(usuarioBd.get().getId());
                usuarioDTO.setUsername(usuarioBd.get().getUsername());
                usuarioDTO.setPassword(usuarioBd.get().getPassword());
                usuarioDTO.setFechaNacimiento(usuarioBd.get().getFechaNacimiento());
                usuarioDTO.setNombre(usuarioBd.get().getNombre());
                usuarioDTO.setApellidos(usuarioBd.get().getApellidos());

                return new ResponseEntity<>(new JwtDTO(credsService.crearJWTLogin(usuarioDTO)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Contraseña errónea.", HttpStatus.UNAUTHORIZED);
            }
            } else {
                return new ResponseEntity<>("Ese nombre de usuario no se encuentra en la base de datos.", HttpStatus.NOT_FOUND);
            }
    }
}
