package com.velazquez.apirestpi.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "duracion", nullable = false)
    private Double duracion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "aireLibre", nullable = false)
    private boolean aireLibre;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "mas18", nullable = false)
    private boolean mas18;

    @ManyToOne
    @JoinColumn(name = "ofertante_id", nullable = false)
    Ofertante ofertante;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    Tipo tipo;

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

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isAireLibre() {
        return aireLibre;
    }

    public void setAireLibre(boolean aireLibre) {
        this.aireLibre = aireLibre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isMas18() {
        return mas18;
    }

    public void setMas18(boolean mas18) {
        this.mas18 = mas18;
    }

    public Ofertante getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertante ofertante) {
        this.ofertante = ofertante;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
        result = prime * result + (aireLibre ? 1231 : 1237);
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + (mas18 ? 1231 : 1237);
        result = prime * result + ((ofertante == null) ? 0 : ofertante.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        Actividad other = (Actividad) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (duracion == null) {
            if (other.duracion != null)
                return false;
        } else if (!duracion.equals(other.duracion))
            return false;
        if (precio == null) {
            if (other.precio != null)
                return false;
        } else if (!precio.equals(other.precio))
            return false;
        if (aireLibre != other.aireLibre)
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (mas18 != other.mas18)
            return false;
        if (ofertante == null) {
            if (other.ofertante != null)
                return false;
        } else if (!ofertante.equals(other.ofertante))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Actividad [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", precio=" + precio
                + ", aireLibre=" + aireLibre + ", fecha=" + fecha + ", mas18=" + mas18 + ", ofertante=" + ofertante
                + ", tipo=" + tipo + "]";
    }
}
