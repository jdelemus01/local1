package com.velazquez.apirestpi.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "consumidorActividad")
@IdClass(ConsumidorActividadId.class)
public class ConsumidorActvidad implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idConsumidor", insertable = false, updatable = false)
    private Consumidor consumidor;

    @Id
    @ManyToOne
    @JoinColumn(name = "idActividad", insertable = false, updatable = false)
    private Actividad actividad;

    public ConsumidorActvidad(Consumidor consumidor, Actividad actividad) {
        this.consumidor = consumidor;
        this.actividad = actividad;
    }

    public ConsumidorActvidad() {
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
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
        ConsumidorActvidad other = (ConsumidorActvidad) obj;
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
        return "ConsumidorActvidad [consumidor=" + consumidor + ", actividad=" + actividad + "]";
    }

}
