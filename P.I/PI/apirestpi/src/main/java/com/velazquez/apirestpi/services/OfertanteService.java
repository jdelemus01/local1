package com.velazquez.apirestpi.services;

import java.util.List;

import com.velazquez.apirestpi.models.Ofertante;

public interface OfertanteService {

    public List<Ofertante> getAllOfertantes();
    public Ofertante getOfertanteById(Long id);

    public void deleteOfertante(Ofertante ofertante);
    
    public Ofertante insertOfertante(Ofertante ofertante);
    
    public Ofertante updateOfertante(Ofertante ofertante);
}
