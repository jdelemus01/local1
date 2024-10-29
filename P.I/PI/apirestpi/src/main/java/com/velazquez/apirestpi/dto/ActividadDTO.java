package com.velazquez.apirestpi.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ActividadDTO implements Serializable {

    private Long id;

    private String nombre;

    private Double duracion;

    private Double precio;

    private boolean aireLibre;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha;

    private boolean mas18;

    private Long tipoId;

    private Long ofertanteId;


    public ActividadDTO(Long id, String nombre, Double duracion, Double precio, boolean aireLibre, Date fecha,
            boolean mas18, Long tipoId, Long ofertanteId) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.aireLibre = aireLibre;
        this.fecha = fecha;
        this.mas18 = mas18;
        this.tipoId = tipoId;
        this.ofertanteId = ofertanteId;
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

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Long getOfertanteId() {
        return ofertanteId;
    }

    public void setOfertanteId(Long ofertanteId) {
        this.ofertanteId = ofertanteId;
    }


    @Override
    public String toString() {
        return "ActividadDTO [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", precio=" + precio
                + ", aireLibre=" + aireLibre + ", fecha=" + fecha + ", mas18=" + mas18 + ", tipoId=" + tipoId
                + ", ofertanteId=" + ofertanteId + "]";
    }
}
