package com.velazquez.apirestpi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.dto.ActividadDTO;
import com.velazquez.apirestpi.dto.TipoDTO;
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
    public Optional<TipoDTO> getTipoById(Long id) {
        Optional<Tipo> tipoGet = tipoRepositorio.findById(id);

        //Esta mierda de aquí hay que entenderla
            return tipoGet.map(tipo -> {
                List<ActividadDTO> actividades = tipo.getActividades().stream().map(
                        actividad -> new ActividadDTO(actividad.getId(), actividad.getNombre(), actividad.getDuracion(),
                                actividad.getPrecio(),
                                actividad.isAireLibre(), actividad.getFecha(), actividad.isMas18(), tipo.getId(),
                                actividad.getOfertante().getId()))
                        .collect(Collectors.toList());
                return new TipoDTO(tipo.getId(), tipo.getNombre(), actividades);
            });
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
