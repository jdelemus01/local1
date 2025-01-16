package com.velazquez.apirestpi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.config.JWTProvider;
import com.velazquez.apirestpi.config.MainSecurityConfiguration;
import com.velazquez.apirestpi.dto.UsuarioDTO;
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
        if (!usuarioBd.isPresent()) {
            usuario.setPassword(MainSecurityConfiguration.getPasswordEncoder().encode(usuario.getPassword()));
            usuarioService.insertUsuario(usuario);
        }
        return registrado;
    }

    @Override
    public String crearJWTLogin(UsuarioDTO usuarioDTO) {
        return jwtProvider.crearToken(usuarioDTO);
    }

}
