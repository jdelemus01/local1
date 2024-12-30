package com.velazquez.apirestpi.services;

import java.util.List;

import com.velazquez.apirestpi.models.ConsumidorActividad;
import com.velazquez.apirestpi.models.ConsumidorActividadId;

public interface ConsumoActividadService {

    public List<ConsumidorActividad> getAllConsAct();
    public List<ConsumidorActividad> getConsumoActividadByConsumidor(Long id);
    public List<ConsumidorActividad> getConsumoActividadByActividad(Long id);
    public ConsumidorActividad getConsumoActividad(ConsumidorActividadId consumoId);

    public ConsumidorActividad insertConsumo(ConsumidorActividad consumo);
    
    public void deleteConsumo(ConsumidorActividad consumo);
}
