package com.velazquez.apirestpi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.velazquez.apirestpi.models.Consumidor;

@Repository
public interface ConsumidorRepositorio extends JpaRepository<Consumidor, Long>{
    @Query("SELECT c FROM Consumidor c WHERE c.usuario.id = :username")
    public Optional<Consumidor> findByUsuario(@Param("username") Long username);

    public Optional<Consumidor> findByEmail(String email);
}
