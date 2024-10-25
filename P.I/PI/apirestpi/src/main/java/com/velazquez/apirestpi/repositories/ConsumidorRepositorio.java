package com.velazquez.apirestpi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velazquez.apirestpi.models.Consumidor;

@Repository
public interface ConsumidorRepositorio extends JpaRepository<Consumidor, Long>{}
