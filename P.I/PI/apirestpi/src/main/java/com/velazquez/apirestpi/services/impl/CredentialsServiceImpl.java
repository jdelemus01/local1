package com.velazquez.apirestpi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.config.JWTProvider;
import com.velazquez.apirestpi.config.MainSecurityConfiguration;
import com.velazquez.apirestpi.dto.CredencialesDTO;
import com.velazquez.apirestpi.models.Usuario;
import com.velazquez.apirestpi.services.CredentialsService;

@Service
public class CredentialsServiceImpl implements CredentialsService {


    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private final JWTProvider jwtProvider;
    
    public CredentialsServiceImpl() {
        this.jwtProvider = new JWTProvider();
        }   

    @Override
    public boolean registro(Usuario usuario) {
        boolean registrado = false;
        Optional<Usuario> usuarioBd = usuarioService.getUsuarioByUsername(usuario.getUsername());
        if(!usuarioBd.isPresent()){
            usuario.setContrasenya(MainSecurityConfiguration.getPasswordEncoder().encode(usuario.getContrasenya()));
            usuarioService.insertUsuario(usuario);
        }
        return registrado;
    }

    @Override
    public String crearJWTLogin(CredencialesDTO creds) {
        String jwt = "";
        Optional<Usuario> usuario = usuarioService.getUsuarioByUsername(creds.getUsuario());
        if(usuario.isPresent()){
            /*
            if(MainSecurityConfiguration.getPasswordEncoder().matches(creds.getContrasenya(), usuario.get().getContrasenya())){
                jwt = jwtProvider.crearToken(usuario.get());
            } 
             */
            if(usuario.get().getContrasenya().equals(creds.getContrasenya())){
                jwt = jwtProvider.crearToken(usuario.get());
            }
        }
        return jwt;
    }

}
