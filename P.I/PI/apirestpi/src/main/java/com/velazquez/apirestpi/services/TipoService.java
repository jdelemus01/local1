package com.velazquez.apirestpi.services;

import java.util.List;

import com.velazquez.apirestpi.models.Tipo;

public interface TipoService {

    public List<Tipo> getAllTipos();
    public Tipo getTipoById(Long id);

    public Tipo insertTipo(Tipo tipo);
}
