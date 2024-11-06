package com.velazquez.apirestpi.dto;

import java.io.Serializable;
import java.util.List;

public class TipoDTO implements Serializable {

    private Long id;

    private String nombre; 

    private List<ActividadDTO> actividades;


    public TipoDTO(Long id, String nombre, List<ActividadDTO> acts) {
        this.id = id;
        this.nombre = nombre;
        this.actividades = acts;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ActividadDTO> getActividades() {
        return actividades;
    }


    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Tipo [id=" + id + ", nombre=" + nombre+ "]";
    }
}
