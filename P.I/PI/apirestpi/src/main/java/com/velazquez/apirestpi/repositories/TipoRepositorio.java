package com.velazquez.apirestpi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velazquez.apirestpi.models.Tipo;

@Repository
public interface TipoRepositorio extends JpaRepository<Tipo, Long>{}
