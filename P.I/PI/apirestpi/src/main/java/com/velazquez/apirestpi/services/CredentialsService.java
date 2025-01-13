package com.velazquez.apirestpi.services;


import com.velazquez.apirestpi.dto.CredencialesDTO;

public interface CredentialsService {

    public String crearJWTLogin(CredencialesDTO creds);
}
