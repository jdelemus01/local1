package com.velazquez.apirestpi.services.impl;

import java.util.List;
import java.util.Optional;

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
    public Optional<Ofertante> getOfertanteById(Long id) {
        return ofertanteRepositorio.findById(id);
    }

    

    @Override
    public Optional<Ofertante> getOfertanteByUsername(Long id) {
        return ofertanteRepositorio.findByUsuario(id);
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

/*
 * @Override
 * public Optional<OfertanteDTO> getOfertanteById(Long id) {
 * Optional<Ofertante> ofertanteGet = ofertanteRepositorio.findById(id);
 * 
 * return ofertanteGet.map(ofertante -> {
 * List<ActividadDTO> actividades = ofertante.getActividades().stream().map(
 * actividad -> new ActividadDTO(actividad.getId(), actividad.getNombre(),
 * actividad.getDuracion(),
 * actividad.getPrecio(),
 * actividad.isAireLibre(), actividad.getFecha(), actividad.isMas18(),
 * ofertante.getId(),
 * actividad.getOfertante().getId()))
 * .collect(Collectors.toList());
 * return new OfertanteDTO(ofertante.getId(), ofertante.getNombre(),
 * ofertante.getApellido(),
 * ofertante.getEmail(), ofertante.getUsuario().getUsername(), actividades);
 * });
 * }
 */
