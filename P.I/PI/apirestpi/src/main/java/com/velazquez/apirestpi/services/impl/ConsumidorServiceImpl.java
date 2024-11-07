package com.velazquez.apirestpi.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.Consumidor;
import com.velazquez.apirestpi.repositories.ConsumidorRepositorio;
import com.velazquez.apirestpi.services.ConsumidorService;

@Service
public class ConsumidorServiceImpl implements ConsumidorService {

    private final Logger log = LoggerFactory.getLogger(ConsumidorServiceImpl.class);

    @Autowired
    ConsumidorRepositorio consumidorRepositorio;

    
    @Override
    public List<Consumidor> getAllConsumidores() {
        log.info("El método getAllConsumidores devuelve: " + consumidorRepositorio.findAll());
        return consumidorRepositorio.findAll();
    }

    @Override
    public Optional<Consumidor> getConsumidorById(Long id) {
        Optional<Consumidor> consumidorGet = consumidorRepositorio.findById(id);
        return consumidorGet;
    }

    @Override
    public Optional<Consumidor> getConsumidorByUsuario(Long username) {
        Optional<Consumidor> consumidorGet = consumidorRepositorio.findByUsuario(username);
        return consumidorGet;
    }

    @Override
    public Consumidor insertConsumidor(Consumidor consumidor) {
        Consumidor consumidorIns = new Consumidor();

        if (consumidor != null && consumidor.getUsuario() != null) {
            consumidorIns = consumidorRepositorio.save(consumidor);
        }

        log.info("Se ha insertado: " +consumidorIns.toString());
        
        return consumidorIns;
    }
    
    @Override
    public Consumidor updateConsumidor(Consumidor consumidor) {
        Consumidor consumidorIns = new Consumidor();
        
        if (consumidor != null && consumidor.getId() != null) {
            consumidorIns = consumidorRepositorio.save(consumidor);
        }
        
        log.info("Se ha actualizado: " +consumidorIns.toString());
        
        return consumidorIns;
    }

    @Override
    public void deleteConsumidor(Consumidor consumidor) {
        consumidorRepositorio.delete(consumidor);
        log.info("Ha sido borrado: " + consumidor.toString());
    }
}
