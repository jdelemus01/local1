package com.velazquez.apirestpi.models;

import java.io.Serializable;

public class ConsumidorActividadId implements Serializable {

    private Long consumidor;
    private Long actividad;

    public ConsumidorActividadId() {
    }

    public Long getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Long consumidor) {
        this.consumidor = consumidor;
    }

    public Long getActividad() {
        return actividad;
    }

    public void setActividad(Long actividad) {
        this.actividad = actividad;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((consumidor == null) ? 0 : consumidor.hashCode());
        result = prime * result + ((actividad == null) ? 0 : actividad.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConsumidorActividadId other = (ConsumidorActividadId) obj;
        if (consumidor == null) {
            if (other.consumidor != null)
                return false;
        } else if (!consumidor.equals(other.consumidor))
            return false;
        if (actividad == null) {
            if (other.actividad != null)
                return false;
        } else if (!actividad.equals(other.actividad))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConsumidorActividadId [consumidor=" + consumidor + ", actividad=" + actividad + "]";
    }

}
