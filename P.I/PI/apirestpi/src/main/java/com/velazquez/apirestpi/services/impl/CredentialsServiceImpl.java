package com.velazquez.apirestpi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.velazquez.apirestpi.config.JWTProvider;
import com.velazquez.apirestpi.dto.ConsumidorDTO;
import com.velazquez.apirestpi.dto.OfertanteDTO;
import com.velazquez.apirestpi.models.Consumidor;
import com.velazquez.apirestpi.models.Ofertante;
import com.velazquez.apirestpi.services.CredentialsService;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private ConsumidorServiceImpl consumidorService;

    @Autowired
    private OfertanteServiceImpl ofertanteService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    private final JWTProvider jwtProvider;

    public CredentialsServiceImpl() {
        this.jwtProvider = new JWTProvider();
    }   

    @Override
    public void registroConsumidor(ConsumidorDTO consumidorDTO) {
        // TODO Auto-generated method stub
    }

    @Override
    public void registroOfertante(OfertanteDTO ofertanteDTO) {
        // TODO Auto-generated method stub
    }

    @Override
    public String loginConsumidor(String email, String contrasenya) {
        String jwt = "";
        Optional<Ofertante> ofertante = ofertanteService.getOfertanteByEmail(email);

        if(ofertante.isPresent()){
            jwt = jwtProvider.crearToken(, "ofe")
        }
        return jwt;
    }

    @Override
    public Optional<Ofertante> loginOfertante(String email, String contrasenya) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

}
