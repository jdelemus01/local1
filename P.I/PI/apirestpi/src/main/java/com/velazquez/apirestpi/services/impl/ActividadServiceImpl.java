package com.velazquez.apirestpi.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.Actividad;
import com.velazquez.apirestpi.repositories.ActividadRepositorio;
import com.velazquez.apirestpi.services.ActividadService;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final Logger log = LoggerFactory.getLogger(ActividadServiceImpl.class);


    @Autowired
    ActividadRepositorio actividadRepositorio;

    @Override
    public Actividad getActividadById(Long id) {
        log.info("getActividadById devuelve: " + actividadRepositorio.getReferenceById(id).toString());
        return actividadRepositorio.getReferenceById(id);
    }

    @Override
    public List<Actividad> getActividadesAireLibre(boolean aireLibre) {
        log.info("getActividadesAireLibre devuelve: " + actividadRepositorio.findByAireLibre(aireLibre).toString());
        return actividadRepositorio.findByAireLibre(aireLibre);
    }

    @Override
    public List<Actividad> getActividadesmas18(boolean mas18) {
        log.info("getActividadesmas18 devuelve: " + actividadRepositorio.findByAireLibre(mas18).toString());
        return actividadRepositorio.findByMas18(mas18);
    }

    @Override
    public List<Actividad> getAllActividades() {
        log.info("getAllActividades devuelve: " + actividadRepositorio.findAll().toString());
        return actividadRepositorio.findAll();
    }

    @Override
    public void deleteActividad(Long id) {
        Actividad actividad = actividadRepositorio.getReferenceById(id);

        if (actividad != null) {
            actividadRepositorio.delete(actividad);
        }
    }

    @Override
    public Actividad insertActividad(Actividad actividad) {
        Actividad actividadIns = new Actividad();

        if (actividad != null && actividad.getOfertante() != null) {
            actividadIns = actividadRepositorio.save(actividad);
        }

        log.info("Ha sido insertado" + actividadIns);

        return actividadIns;

    }

    @Override
    public Actividad updateActividad(Actividad actividad) {
        Actividad actividadIns = new Actividad();

        if (actividad != null && actividad.getId() != null) {
            actividadIns = actividadRepositorio.save(actividad);
        }

        log.info("Ha sido actualizado" + actividadIns);


        return actividadIns;

    }

}
