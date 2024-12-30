package com.velazquez.apirestpi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.models.Ofertante;
import com.velazquez.apirestpi.models.Usuario;
import com.velazquez.apirestpi.services.impl.OfertanteServiceImpl;
import com.velazquez.apirestpi.services.impl.UsuarioServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ofertante")
@CrossOrigin
public class OfertanteController {

    @Autowired
    OfertanteServiceImpl ofertanteServiceImpl;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/allOfertantes")
    public List<Ofertante> getAllOfertantes() {
        return ofertanteServiceImpl.getAllOfertantes();
    }

    @GetMapping("/getOfertante/{id}")
    public ResponseEntity<Ofertante> getOfertante(@PathVariable Long id) {
        Optional<Ofertante> ofertanteGet = ofertanteServiceImpl.getOfertanteById(id);

        if (ofertanteGet.isPresent()) {
            return new ResponseEntity<>(ofertanteGet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getOfertanteUser/{id}")
    public ResponseEntity<Ofertante> getOfertanteByUsername(@PathVariable Long id) {
        Optional<Ofertante> ofertanteGet = ofertanteServiceImpl.getOfertanteByUsername(id);

        if(ofertanteGet.isPresent()){
            return new ResponseEntity<>(ofertanteGet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getOferUsernames")
    public List<String> getOferUsernames() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<Ofertante> ofertantes = ofertanteServiceImpl.getAllOfertantes();
        List<String> usernamesOfers = new ArrayList<>();
        for (Usuario us : usuarios) {
            for (Ofertante cons : ofertantes) {
                if(us.getId() == cons.getUsuario().getId()){
                    usernamesOfers.add(us.getUsername());
                }
            }
        }
        return usernamesOfers;
    }
    

    @PostMapping("/insOfer")
    public ResponseEntity<Ofertante> insertOfertante(@RequestBody Ofertante ofertanteIns) {

        if(ofertanteIns == null || ofertanteServiceImpl.getOfertanteById(ofertanteIns.getId()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            if(!(usuarioService.getUsuarioByUsername(ofertanteIns.getUsuario().getUsername()).isPresent())){
                ofertanteIns.setUsuario(usuarioService.insertUsuario(ofertanteIns.getUsuario()));;
                ofertanteServiceImpl.insertOfertante(ofertanteIns);
    
                return new ResponseEntity<>(ofertanteIns, HttpStatus.CREATED);
            } else {
                ofertanteServiceImpl.insertOfertante(ofertanteIns);
    
                return new ResponseEntity<>(ofertanteIns, HttpStatus.CREATED);
            }
        }
    }

}
