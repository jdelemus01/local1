package com.velazquez.apirestpi.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.config.MainSecurityConfiguration;
import com.velazquez.apirestpi.models.Usuario;
import com.velazquez.apirestpi.repositories.UsuarioRepositorio;
import com.velazquez.apirestpi.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired UsuarioRepositorio usuarioRepositorio;

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
         return usuarioRepositorio.findById(id);
    }

    @Override
    public Optional<Usuario> getUsuarioByUsername(String usuario){
        return usuarioRepositorio.findByUsername(usuario);
    }

    public List<Usuario> getAllUsuarios(){
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario insertUsuario(Usuario usuario) {
        Usuario usuarioIns = null;

        if(usuario != null){
            usuarioIns = usuarioRepositorio.save(usuario);
        }

        log.info("Se ha insertado: " + usuarioIns);

        return usuarioIns;
    }
    
}
