package com.velazquez.apirestpi.services;

import java.util.List;
import java.util.Optional;

import com.velazquez.apirestpi.models.Ofertante;

public interface OfertanteService {

    public List<Ofertante> getAllOfertantes();
    public Optional<Ofertante> getOfertanteById(Long id);
    public Optional<Ofertante> getOfertanteByUsername(Long id);
    public Optional<Ofertante> getOfertanteByEmail(String email);

    public void deleteOfertante(Ofertante ofertante);

    public Ofertante insertOfertante(Ofertante ofertante);

    public Ofertante updateOfertante(Ofertante ofertante);
}
