package com.velazquez.apirestpi.services;

import java.util.List;
import java.util.Optional;

import com.velazquez.apirestpi.models.Actividad;

public interface ActividadService {

    public List<Actividad> getAllActividades();
    public Optional<Actividad> getActividadById(Long id);
    public List<Actividad> getActividadesmas18(boolean mas18);
    public List<Actividad> getActividadesAireLibre(boolean aireLibre);
    public List<Actividad> getActividadesByOfertante(Long id);

    public void deleteActividad(Long id);

    public Actividad updateActividad(Actividad actividad);

    public Actividad insertActividad(Actividad actividad);
}
