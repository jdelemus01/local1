package com.velazquez.apirestpi.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "OFERTANTE")
public class Ofertante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaNacimiento;

    @OneToOne
    @JoinColumn(name = "username")
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
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
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (fechaNacimiento == null) {
            if (other.fechaNacimiento != null)
                return false;
        } else if (!fechaNacimiento.equals(other.fechaNacimiento))
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
        return "Ofertante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", password=" + password
                + ", fechaNacimiento=" + fechaNacimiento + ", usuario=" + usuario + ", actividades=" + actividades
                + ", rol=" + rol + "]";
    }

    
}
