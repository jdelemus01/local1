package com.velazquez.apirestpi.dto;

import java.io.Serializable;

public class CredencialesDTO implements Serializable{

    private String usuario;
    private String contrasenya;

    public CredencialesDTO(){}

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasenya() {
        return contrasenya;
    }
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
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
        CredencialesDTO other = (CredencialesDTO) obj;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        if (contrasenya == null) {
            if (other.contrasenya != null)
                return false;
        } else if (!contrasenya.equals(other.contrasenya))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CredencialesDTO [usuario=" + usuario + ", contrasenya=" + contrasenya + "]";
    }

    
}
