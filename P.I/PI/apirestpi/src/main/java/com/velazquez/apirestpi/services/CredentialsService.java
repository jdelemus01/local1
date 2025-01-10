package com.velazquez.apirestpi.services;


import com.velazquez.apirestpi.dto.ConsumidorDTO;
import com.velazquez.apirestpi.dto.OfertanteDTO;

public interface CredentialsService {

    public String loginConsumidor(String email, String contrasenya);

    public String loginOfertante(String email, String contrasenya);

    public void registroConsumidor(ConsumidorDTO consumidorDTO);

    public void registroOfertante(OfertanteDTO ofertanteDTO);
}
