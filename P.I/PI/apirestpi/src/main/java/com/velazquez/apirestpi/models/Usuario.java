package com.velazquez.apirestpi.models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "contrasenya", unique = true, nullable = false)
    private String contrasenya;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Consumidor consumidor;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ofertante ofertante; 

     private boolean activo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
    

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public Ofertante getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertante ofertante) {
        this.ofertante = ofertante;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
        result = prime * result + ((consumidor == null) ? 0 : consumidor.hashCode());
        result = prime * result + ((ofertante == null) ? 0 : ofertante.hashCode());
        result = prime * result + (activo ? 1231 : 1237);
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (contrasenya == null) {
            if (other.contrasenya != null)
                return false;
        } else if (!contrasenya.equals(other.contrasenya))
            return false;
        if (consumidor == null) {
            if (other.consumidor != null)
                return false;
        } else if (!consumidor.equals(other.consumidor))
            return false;
        if (ofertante == null) {
            if (other.ofertante != null)
                return false;
        } else if (!ofertante.equals(other.ofertante))
            return false;
        if (activo != other.activo)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username + ", contrasenya=" + contrasenya + ", consumidor="
                + consumidor + ", ofertante=" + ofertante + ", activo=" + activo + "]";
    }

     
}
