package com.velazquez.apirestpi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.dto.CredencialesDTO;
import com.velazquez.apirestpi.services.impl.CredentialsServiceImpl;



@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private CredentialsServiceImpl credsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredencialesDTO creds) {
        String jwt = credsService.crearJWTLogin(creds);
        if(jwt != ""){
            return new ResponseEntity<>(jwt ,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(jwt ,HttpStatus.NOT_FOUND);
        }
    }
}
