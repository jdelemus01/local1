package com.velazquez.apirestpi.dto;

import java.io.Serializable;
import java.util.List;

public class OfertanteDTO implements Serializable {

    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private String contrasenya;

    private Long usuarioId;
    
    private List<ActividadDTO> actividades;

    public OfertanteDTO(Long id, String nombre, String apellido, String email, String contrasenya, Long usuarioId,
            List<ActividadDTO> actividades) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenya = contrasenya;
        this.usuarioId = usuarioId;
        this.actividades = actividades;
    }

    public OfertanteDTO() {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
        result = prime * result + ((actividades == null) ? 0 : actividades.hashCode());
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
        OfertanteDTO other = (OfertanteDTO) obj;
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
        if (apellido == null) {
            if (other.apellido != null)
                return false;
        } else if (!apellido.equals(other.apellido))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (contrasenya == null) {
            if (other.contrasenya != null)
                return false;
        } else if (!contrasenya.equals(other.contrasenya))
            return false;
        if (usuarioId == null) {
            if (other.usuarioId != null)
                return false;
        } else if (!usuarioId.equals(other.usuarioId))
            return false;
        if (actividades == null) {
            if (other.actividades != null)
                return false;
        } else if (!actividades.equals(other.actividades))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OfertanteDTO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
                + ", contrasenya=" + contrasenya + ", usuarioId=" + usuarioId + ", actividades=" + actividades + "]";
    }

    
}
