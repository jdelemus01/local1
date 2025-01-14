package com.velazquez.apirestpi.services;


import com.velazquez.apirestpi.dto.CredencialesDTO;
import com.velazquez.apirestpi.models.Usuario;

public interface CredentialsService {

    public String crearJWTLogin(CredencialesDTO creds);

    public boolean registro(Usuario usuario);
}
