package com.velazquez.apirestpi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.models.ConsumidorActividad;
import com.velazquez.apirestpi.models.ConsumidorActividadId;
import com.velazquez.apirestpi.repositories.ConsumoActConsRepositorio;

@Service
public class ConsumoActividadServiceImpl {

    @Autowired
    ConsumoActConsRepositorio consumoActConsRepositorio;

    public List<ConsumidorActividad> getAllConsAct() {
        return consumoActConsRepositorio.findAll();
    }

    public List<ConsumidorActividad> getConsumoActividadByConsumidor(Long id) {
        return consumoActConsRepositorio.findByConsumidor(id);
    }

    public List<ConsumidorActividad> getConsumoActividadByActividad(Long id) {
        return consumoActConsRepositorio.findByActividad(id);
    }

    public ConsumidorActividad getConsumoActividad(ConsumidorActividadId consumoId) {
        return consumoActConsRepositorio.findById(consumoId).get();
    }

    public ConsumidorActividad insertConsumo(ConsumidorActividad consumo) {
        return consumoActConsRepositorio.save(consumo);
    }

    public void deleteConsumo(ConsumidorActividad consumo) {
        consumoActConsRepositorio.delete(consumo);
    }
}
