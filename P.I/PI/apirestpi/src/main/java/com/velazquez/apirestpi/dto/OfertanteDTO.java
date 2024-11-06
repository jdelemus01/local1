package com.velazquez.apirestpi.dto;

import java.io.Serializable;
import java.util.List;

public class OfertanteDTO implements Serializable {

    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private String username;

    private List<ActividadDTO> actividades;

    public OfertanteDTO(Long id, String nombre, String apellido, String email, String username,
            List<ActividadDTO> actividades) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.username = username;
        this.actividades = actividades;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Ofertante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
                + ", username=" + username + "]";
    }
}
