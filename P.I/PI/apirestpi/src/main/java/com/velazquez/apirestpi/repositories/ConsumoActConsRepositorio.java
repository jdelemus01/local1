package com.velazquez.apirestpi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.velazquez.apirestpi.models.ConsumidorActividad;
import com.velazquez.apirestpi.models.ConsumidorActividadId;

@Repository
public interface ConsumoActConsRepositorio extends JpaRepository<ConsumidorActividad, ConsumidorActividadId>{

    @Query("SELECT ca FROM ConsumidorActividad ca WHERE ca.consumidor.id = :consumidorId")
    List<ConsumidorActividad> findByConsumidor(@Param("consumidorId") Long consumidorId);

    @Query("SELECT ca FROM ConsumidorActividad ca WHERE ca.actividad.id = :actividadId")
    List<ConsumidorActividad> findByActividad(@Param("actividadId") Long actividadId);
}
