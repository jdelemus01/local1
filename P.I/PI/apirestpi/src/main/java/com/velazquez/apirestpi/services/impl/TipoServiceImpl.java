package com.velazquez.apirestpi.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.Tipo;
import com.velazquez.apirestpi.repositories.TipoRepositorio;
import com.velazquez.apirestpi.services.TipoService;

@Service
public class TipoServiceImpl implements TipoService {

    private final Logger log = LoggerFactory.getLogger(TipoServiceImpl.class);

    @Autowired
    TipoRepositorio tipoRepositorio;

    @Override
    public List<Tipo> getAllTipos() {
        log.info("El método getAllTipos ha recibido: " + tipoRepositorio.findAll());
        return tipoRepositorio.findAll();
    }

    @Override
    public Tipo getTipoById(Long id) {
        log.info("El método getTipoById ha recibido: " + tipoRepositorio.getReferenceById(id));
        return tipoRepositorio.getReferenceById(id);
    }

    @Override
    public Tipo insertTipo(Tipo tipo) {
        Tipo tipoIns = null;

        if (tipo != null) {
            tipoIns = tipoRepositorio.save(tipo);
        }

        log.info("Ha sido insertado: " + tipoIns);

        return tipoIns;
    }

}
