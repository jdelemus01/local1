package com.velazquez.apirestpi.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.velazquez.apirestpi.models.Usuario;


@Component
public class JWTProvider {

    @Value("${jwt.claveSecreta}")
    private String claveSecreta;
    
    
    public String crearToken(Usuario usuario) {
        Algorithm algoritmo = Algorithm.HMAC256(claveSecreta);
        Instant ahora = Instant.now();

        Instant limite = ahora.plus(1, ChronoUnit.HOURS);

            return JWT.create()
                .withSubject(usuario.getUsername())
                .withIssuedAt(Date.from(ahora))
                .withExpiresAt(Date.from(limite))
                .sign(algoritmo);
    }

    /*
    public Authentication obtenerUsuario(){
        Algorithm algoritmo = Algorithm.HMAC512(claveSecreta); 

        JWTVerifier verificador = JWT.require(algoritmo).build();   


    }
     */
}
