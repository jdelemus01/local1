package com.velazquez.apirestpi.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ConsumidorDTO implements Serializable {

    private Long id;

    private String nombre;

    private String apellido;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaNacimiento;

    private String email;

    private String contrasenya;

    private Long usuarioId; 

    public ConsumidorDTO() {
    }

    public ConsumidorDTO(Long id, String nombre, String apellido, Date fechaNacimiento, String email, String contrasenya, Long usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.contrasenya = contrasenya;
        this.usuarioId = usuarioId;

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
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
        ConsumidorDTO other = (ConsumidorDTO) obj;
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
        if (fechaNacimiento == null) {
            if (other.fechaNacimiento != null)
                return false;
        } else if (!fechaNacimiento.equals(other.fechaNacimiento))
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
        return true;
    }


    @Override
    public String toString() {
        return "ConsumidorDTO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
                + fechaNacimiento + ", email=" + email + ", contrasenya=" + contrasenya + ", usuarioId=" + usuarioId
                + "]";
    }


    
}
