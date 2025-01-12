package com.velazquez.apirestpi.services;


import com.velazquez.apirestpi.models.Consumidor;
import com.velazquez.apirestpi.models.Ofertante;

public interface CredentialsService {

    public String loginConsumidor(String email, String contrasenya);

    public String loginOfertante(String email, String contrasenya);

    public boolean registroConsumidor(Consumidor consumidor);

    public boolean registroOfertante(Ofertante ofertante);
}
