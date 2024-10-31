package com.velazquez.apirestpi.services;

import java.util.List;

import com.velazquez.apirestpi.models.Usuario;

public interface UsuarioService {

    public List<Usuario> getAllUsuarios();

    public Usuario insertUsuario(Usuario usuario);
}
