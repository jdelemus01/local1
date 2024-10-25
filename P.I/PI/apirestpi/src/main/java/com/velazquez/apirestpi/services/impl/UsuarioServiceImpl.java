package com.velazquez.apirestpi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velazquez.apirestpi.models.Usuario;
import com.velazquez.apirestpi.repositories.UsuarioRepositorio;
import com.velazquez.apirestpi.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired UsuarioRepositorio usuarioRepositorio;

    @Override
    public Usuario insertUsuario(Usuario usuario) {
        Usuario usuarioIns = null;

        if(usuario != null){
            usuarioIns = usuarioRepositorio.save(usuario);
        }

        return usuarioIns;
    }
    
}
