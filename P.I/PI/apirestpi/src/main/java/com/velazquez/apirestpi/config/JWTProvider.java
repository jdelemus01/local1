package com.velazquez.apirestpi.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.velazquez.apirestpi.dto.UsuarioDTO;

import jakarta.annotation.PostConstruct;

@Component
public class JWTProvider {

    @Value("${security.jwt.token-secret}")
    private String claveSecreta;
    private Algorithm algoritmo; 

    @PostConstruct
    protected void encriptar() {
        claveSecreta = Base64.getEncoder().encodeToString(claveSecreta.getBytes());
        algoritmo = Algorithm.HMAC512(claveSecreta);
    }

    public String crearToken(UsuarioDTO usuarioDTO, String rol) {
        Instant ahora = Instant.now();

        Instant limite = ahora.plus(1, ChronoUnit.HOURS);

            return JWT.create()
                .withSubject(usuarioDTO.getUsername())
                .withClaim("Rol", rol)
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
