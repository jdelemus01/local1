package com.velazquez.apirestpi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.Consumidor;
import com.velazquez.apirestpi.repositories.ConsumidorRepositorio;
import com.velazquez.apirestpi.services.ConsumidorService;

@Service
public class ConsumidorImplService implements ConsumidorService {

    @Autowired
    ConsumidorRepositorio consumidorRepositorio;

    @Override
    public void deleteConsumidor(Consumidor consumidor) {
        consumidorRepositorio.delete(consumidor);
    }

    @Override
    public List<Consumidor> getAllConsumidores() {
        return consumidorRepositorio.findAll();
    }

    @Override
    public Consumidor getConsumidorById(Long id) {
        return consumidorRepositorio.getReferenceById(id);
    }

    @Override
    public Consumidor insertConsumidor(Consumidor consumidor) {
        Consumidor consumidorIns = new Consumidor();

        if (consumidor != null && consumidor.getUsuario() != null) {
            consumidorIns = consumidorRepositorio.save(consumidor);
        }

        return consumidorIns;
    }

    @Override
    public Consumidor updateConsumidor(Consumidor consumidor) {
        Consumidor consumidorIns = new Consumidor();

        if (consumidor != null && consumidor.getId() != null) {
            consumidorIns = consumidorRepositorio.save(consumidor);
        }

        return consumidorIns;
    }

}
