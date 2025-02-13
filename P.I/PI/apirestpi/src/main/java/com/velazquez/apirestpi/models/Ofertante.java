package com.velazquez.apirestpi.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "OFERTANTE")
public class Ofertante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "ofertante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Actividad> actividades = new HashSet<>();

    private String rol = "OFE";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    public String getRol() {
        return rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        result = prime * result + ((actividades == null) ? 0 : actividades.hashCode());
        result = prime * result + ((rol == null) ? 0 : rol.hashCode());
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
        Ofertante other = (Ofertante) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        if (actividades == null) {
            if (other.actividades != null)
                return false;
        } else if (!actividades.equals(other.actividades))
            return false;
        if (rol == null) {
            if (other.rol != null)
                return false;
        } else if (!rol.equals(other.rol))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ofertante [id=" + id + ", email=" + email
                + ", usuario=" + usuario.getUsername()
                + ", actividades=" + actividades + ", rol=" + rol + "]";
    }

}
