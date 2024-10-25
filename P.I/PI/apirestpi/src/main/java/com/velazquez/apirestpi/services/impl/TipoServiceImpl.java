package com.velazquez.apirestpi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.Tipo;
import com.velazquez.apirestpi.repositories.TipoRepositorio;
import com.velazquez.apirestpi.services.TipoService;

@Service
public class TipoServiceImpl implements TipoService {

    @Autowired
    TipoRepositorio tipoRepositorio;

    @Override
    public List<Tipo> getAllTipos() {
        return tipoRepositorio.findAll();
    }

    @Override
    public Tipo getTipoById(Long id) {
        return tipoRepositorio.getReferenceById(id);
    }

    @Override
    public Tipo insertTipo(Tipo tipo) {
        Tipo tipoIns = null;

        if (tipo != null) {
            tipoIns = tipoRepositorio.save(tipo);
        }

        return tipoIns;
    }

}
