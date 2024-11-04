package com.velazquez.apirestpi.services;

import java.util.List;

import com.velazquez.apirestpi.dto.ActividadDTO;
import com.velazquez.apirestpi.models.Actividad;

public interface ActividadService {

    public List<Actividad> getAllActividades();
    public Actividad getActividadById(Long id);
    public ActividadDTO getActividadByIdDTO(Long id);
    public List<Actividad> getActividadesmas18(boolean mas18);
    public List<Actividad> getActividadesAireLibre(boolean aireLibre);

    public void deleteActividad(Long id);

    public Actividad updateActividad(Actividad actividad);

    public Actividad insertActividad(Actividad actividad);
}
