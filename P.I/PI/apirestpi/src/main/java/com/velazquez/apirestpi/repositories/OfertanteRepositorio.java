package com.velazquez.apirestpi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velazquez.apirestpi.models.Ofertante;

@Repository
public interface OfertanteRepositorio extends JpaRepository<Ofertante, Long>{}
