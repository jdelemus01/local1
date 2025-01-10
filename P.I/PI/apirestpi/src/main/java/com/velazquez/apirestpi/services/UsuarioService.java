package com.velazquez.apirestpi.services;

import java.util.List;
import java.util.Optional;

import com.velazquez.apirestpi.models.Usuario;

public interface UsuarioService {

   // public Optional<Usuario> getUsuarioByUsername(String username);

    public Optional<Usuario> getUsuarioById(Long id);

    public List<Usuario> getAllUsuarios();

    public Usuario insertUsuario(Usuario usuario);
}
