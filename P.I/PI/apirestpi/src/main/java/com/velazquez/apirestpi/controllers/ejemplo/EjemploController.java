package com.velazquez.apirestpi.controllers.ejemplo;

//import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/ejemploController")
public class EjemploController {

    @GetMapping
    public ResponseEntity<String> ejemplo() {
        return ResponseEntity.ok("Esto es un ejemplo de cómo funciona un Get para que Joselito lo entienda");
    }
    
}
