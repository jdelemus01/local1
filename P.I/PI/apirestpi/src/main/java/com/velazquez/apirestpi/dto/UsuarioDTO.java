package com.velazquez.apirestpi.dto;

import java.io.Serializable;
import java.util.Date;


public class UsuarioDTO implements Serializable {

    private Long id;

    private String nombre;

    private String apellidos;

    private Date fechaNacimiento;

    private String username;

    private String password;

    private Long ofertante;

    private Long consumidor;

    public UsuarioDTO(){}

    public UsuarioDTO(Long id, String nombre, String apellidos, Date fechaNacimiento, String username, String password,
            Long ofertante, Long consumidor) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.username = username;
        this.password = password;
        this.ofertante = ofertante;
        this.consumidor = consumidor;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getOfertante() {
        return ofertante;
    }

    public void setOfertante(Long ofertante) {
        this.ofertante = ofertante;
    }

    public Long getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Long consumidor) {
        this.consumidor = consumidor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((ofertante == null) ? 0 : ofertante.hashCode());
        result = prime * result + ((consumidor == null) ? 0 : consumidor.hashCode());
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
        UsuarioDTO other = (UsuarioDTO) obj;
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
        if (apellidos == null) {
            if (other.apellidos != null)
                return false;
        } else if (!apellidos.equals(other.apellidos))
            return false;
        if (fechaNacimiento == null) {
            if (other.fechaNacimiento != null)
                return false;
        } else if (!fechaNacimiento.equals(other.fechaNacimiento))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (ofertante == null) {
            if (other.ofertante != null)
                return false;
        } else if (!ofertante.equals(other.ofertante))
            return false;
        if (consumidor == null) {
            if (other.consumidor != null)
                return false;
        } else if (!consumidor.equals(other.consumidor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
                + fechaNacimiento + ", username=" + username + ", password=" + password + ", ofertante=" + ofertante
                + ", consumidor=" + consumidor + "]";
    }

}
