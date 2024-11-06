package com.velazquez.apirestpi.services;

import java.util.List;
import java.util.Optional;

import com.velazquez.apirestpi.dto.TipoDTO;
import com.velazquez.apirestpi.models.Tipo;

public interface TipoService {

    public List<Tipo> getAllTipos();
    public Optional<TipoDTO> getTipoById(Long id);

    public Tipo insertTipo(Tipo tipo);
}
