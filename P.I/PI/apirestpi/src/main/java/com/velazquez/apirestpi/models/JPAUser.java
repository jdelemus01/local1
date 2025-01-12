/*
package com.velazquez.apirestpi.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JPAUser implements UserDetails{
    private Usuario usuario;

    public JPAUser(Usuario usuario) { 
        this.usuario = usuario; 
    }

    //Esto establece los roles de los usuarios (Puede añadir dos roles a un usuario, pero el de admin no, porque solo hay uno y soy yo).
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(usuario.getConsumidor() != null){
            authorities.add(new SimpleGrantedAuthority("CON"));
        }
        if(usuario.getOfertante() != null){
            authorities.add(new SimpleGrantedAuthority("OFE"));
        }
        return authorities;
    }
    

    //Este método devuelve las contraseñas separadas por un espacio del usuario en caso de que tenga dos cuentas, y la contraseña normal en caso de que solo tenga una cuenta.
    //(Voy a meter una regla de validación al campo contraseña para que no pueda contener espacios).
    
    @Override
    public String getPassword() {
        if(this.usuario.getConsumidor() != null || this.usuario.getOfertante() != null){
            return this.usuario.getConsumidor().getPassword() + ' ' + this.usuario.getOfertante().getContrasenya();
        } else if(this.usuario.getConsumidor() != null){
                return this.usuario.getConsumidor().getPassword();
            } else if(this.usuario.getOfertante() != null){
                return this.usuario.getOfertante().getContrasenya();
            } else {
                return null;
            }
        }
     

    @Override
    public String getUsername() {
        return this.usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.usuario.isActivo();
    }
}
*/
