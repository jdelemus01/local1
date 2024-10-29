package com.velazquez.apirestpi.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.Ofertante;
import com.velazquez.apirestpi.repositories.OfertanteRepositorio;
import com.velazquez.apirestpi.services.OfertanteService;

@Service
public class OfertanteServiceImpl implements OfertanteService {

    private final Logger log = LoggerFactory.getLogger(OfertanteServiceImpl.class);

    @Autowired
    OfertanteRepositorio ofertanteRepositorio;

    @Override
    public List<Ofertante> getAllOfertantes() {
        log.info("El método getAllOfertantes devuelve: " + ofertanteRepositorio.findAll());
        return ofertanteRepositorio.findAll();
    }

    @Override
    public Ofertante getOfertanteById(Long id) {
        log.info("El método getOfertanteById devuelve: " + ofertanteRepositorio.getReferenceById(id));
        return ofertanteRepositorio.getReferenceById(id);
    }

    @Override
    public void deleteOfertante(Ofertante ofertante) {
        ofertanteRepositorio.delete(ofertante);

        log.info("Ha sido borrado: " + ofertante.toString());
    }

    @Override
    public Ofertante insertOfertante(Ofertante ofertante) {
        Ofertante ofertanteIns = new Ofertante();

        if (ofertante != null && ofertante.getUsuario() != null) {
            ofertanteIns = ofertanteRepositorio.save(ofertante);
        }

        log.info("Ha sido insertado: " + ofertanteIns.toString());

        return ofertanteIns;
    }

    @Override
    public Ofertante updateOfertante(Ofertante ofertante) {

        Ofertante ofertanteIns = new Ofertante();

        if (ofertante != null && ofertante.getId() != null) {
            ofertanteIns = ofertanteRepositorio.save(ofertante);
        }

        log.info("Ha sido actualizado: " + ofertanteIns.toString());

        return ofertanteIns;
    }

}
