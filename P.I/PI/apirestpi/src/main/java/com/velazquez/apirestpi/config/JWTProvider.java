package com.velazquez.apirestpi.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.velazquez.apirestpi.dto.CredencialesDTO;
import com.velazquez.apirestpi.dto.UsuarioDTO;


@Component
public class JWTProvider {

    //@Value("${jwt.claveSecreta}")
    private String claveSecreta="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    
    
    public String crearToken(UsuarioDTO usuarioDto) {
        Algorithm algoritmo = Algorithm.HMAC256(claveSecreta);
        Instant ahora = Instant.now();

        Integer ofe = 1;
        Integer cons = 1;

        if(usuarioDto.getConsumidor() != -1){
            cons = 0;
        }

        if(usuarioDto.getOfertante() != -1){
            ofe = 0;
        }

        Instant limite = ahora.plus(1, ChronoUnit.HOURS);

            return JWT.create()
                .withSubject(usuarioDto.getUsername())
                .withClaim("ofe", ofe)
                .withClaim("cons", cons)
                .withClaim("id", usuarioDto.getId())
                .withIssuedAt(Date.from(ahora))
                .withExpiresAt(Date.from(limite))
                .sign(algoritmo);
    }

    
    public Authentication validarToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(claveSecreta);

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJwt = verifier.verify(token);

        CredencialesDTO creds = new CredencialesDTO();
        creds.setUsuario(decodedJwt.getSubject());

        return new UsernamePasswordAuthenticationToken(creds, null, null);
    }
     
}
