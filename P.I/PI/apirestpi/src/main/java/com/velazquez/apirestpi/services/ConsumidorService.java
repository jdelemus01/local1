package com.velazquez.apirestpi.services;

import java.util.List;
import java.util.Optional;

import com.velazquez.apirestpi.models.Consumidor;

public interface ConsumidorService {

    public List<Consumidor> getAllConsumidores();
    public Optional<Consumidor> getConsumidorById(Long id);
    public Optional<Consumidor> getConsumidorByUsername(Long username);
    public Optional<Consumidor> getConsumidorByEmail(String email);

    public void deleteConsumidor(Consumidor consumidor);

    public Consumidor updateConsumidor(Consumidor consumidor);

    public Consumidor insertConsumidor(Consumidor consumidor);
}
