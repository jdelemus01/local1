package com.velazquez.apirestpi.controllers.ejemplo;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.apache.coyote.Response;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.dto.ActividadDTO;
import com.velazquez.apirestpi.dto.UsuarioDTO;
import com.velazquez.apirestpi.services.impl.UsuarioServiceImpl;


@RestController
@RequestMapping("/usuario") 
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

     @GetMapping("/allUsuarios")
     public ResponseEntity<?> allUsuarios() {
         return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
     }
}
