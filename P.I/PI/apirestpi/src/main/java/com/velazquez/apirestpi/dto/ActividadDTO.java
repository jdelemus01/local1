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

    private Long tipoDTO;

    private Long ofertanteDTO;

    public ActividadDTO(Long id, String nombre, Double duracion, Double precio, boolean aireLibre, Date fecha,
            boolean mas18, Long tipoDTO, Long ofertanteDTO) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.aireLibre = aireLibre;
        this.fecha = fecha;
        this.mas18 = mas18;
        this.tipoDTO = tipoDTO;
        this.ofertanteDTO = ofertanteDTO;
    }

    public ActividadDTO() {
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

    

    public Long getTipoDTO() {
        return tipoDTO;
    }

    public void setTipoDTO(Long tipoDTO) {
        this.tipoDTO = tipoDTO;
    }

    public Long getOfertanteDTO() {
        return ofertanteDTO;
    }

    public void setOfertanteDTO(Long ofertanteDTO) {
        this.ofertanteDTO = ofertanteDTO;
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
        result = prime * result + ((tipoDTO == null) ? 0 : tipoDTO.hashCode());
        result = prime * result + ((ofertanteDTO == null) ? 0 : ofertanteDTO.hashCode());
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
        ActividadDTO other = (ActividadDTO) obj;
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
        if (tipoDTO == null) {
            if (other.tipoDTO != null)
                return false;
        } else if (!tipoDTO.equals(other.tipoDTO))
            return false;
        if (ofertanteDTO == null) {
            if (other.ofertanteDTO != null)
                return false;
        } else if (!ofertanteDTO.equals(other.ofertanteDTO))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ActividadDTO [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", precio=" + precio
                + ", aireLibre=" + aireLibre + ", fecha=" + fecha + ", mas18=" + mas18 + ", tipoDTO=" + tipoDTO
                + ", ofertanteDTO=" + ofertanteDTO + "]";
    }

        
}
