package com.velazquez.apirestpi.api.dto;

import java.io.Serializable;
import com.velazquez.apirestpi.models.Rol;;

public class UsuarioDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Rol rol;


    public UsuarioDTO(Long id, String username, String password, String email, Rol rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }
    

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", rol="
                + rol + "]";
    }

}
