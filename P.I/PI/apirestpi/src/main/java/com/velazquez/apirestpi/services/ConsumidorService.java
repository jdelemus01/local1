package com.velazquez.apirestpi.services;

import java.util.List;

import com.velazquez.apirestpi.models.Consumidor;

public interface ConsumidorService {

    public List<Consumidor> getAllConsumidores();
    public Consumidor getConsumidorById(Long id);

    public void deleteConsumidor(Consumidor consumidor);

    public Consumidor updateConsumidor(Consumidor consumidor);

    public Consumidor insertConsumidor(Consumidor consumidor);
}
