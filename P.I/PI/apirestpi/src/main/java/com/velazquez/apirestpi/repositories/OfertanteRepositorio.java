package com.velazquez.apirestpi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.velazquez.apirestpi.models.Ofertante;

@Repository
public interface OfertanteRepositorio extends JpaRepository<Ofertante, Long>{

    @Query("SELECT o FROM Ofertante o WHERE o.usuario.id = :username")
    public Optional<Ofertante> findByUsuario(@Param("username") Long username);
}
