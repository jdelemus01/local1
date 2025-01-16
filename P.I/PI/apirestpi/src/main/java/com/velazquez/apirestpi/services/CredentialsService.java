package com.velazquez.apirestpi.services;


import com.velazquez.apirestpi.dto.UsuarioDTO;
import com.velazquez.apirestpi.models.Usuario;

public interface CredentialsService {

    public String crearJWTLogin(UsuarioDTO usuarioDTO);

    public boolean registro(Usuario usuario);
}
