package com.velazquez.apirestpi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.Ofertante;
import com.velazquez.apirestpi.repositories.OfertanteRepositorio;
import com.velazquez.apirestpi.services.OfertanteService;

@Service
public class OfertanteServiceImpl implements OfertanteService {

    @Autowired
    OfertanteRepositorio ofertanteRepositorio;

    @Override
    public List<Ofertante> getAllOfertantes() {
        return ofertanteRepositorio.findAll();
    }

    @Override
    public Ofertante getOfertanteById(Long id) {
        return ofertanteRepositorio.getReferenceById(id);
    }

    @Override
    public void deleteOfertante(Ofertante ofertante) {
        ofertanteRepositorio.delete(ofertante);
    }

    @Override
    public Ofertante insertOfertante(Ofertante ofertante) {
        Ofertante ofertanteIns = new Ofertante();

        if (ofertante != null && ofertante.getUsuario() != null) {
            ofertanteIns = ofertanteRepositorio.save(ofertante);
        }

        return ofertanteIns;
    }

    @Override
    public Ofertante updateOfertante(Ofertante ofertante) {

        Ofertante ofertanteIns = new Ofertante();

        if (ofertante != null && ofertante.getId() != null) {
            ofertanteIns = ofertanteRepositorio.save(ofertante);
        }

        return ofertanteIns;
    }

}
