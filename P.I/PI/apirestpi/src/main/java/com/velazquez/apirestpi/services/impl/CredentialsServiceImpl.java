package com.velazquez.apirestpi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.config.JWTProvider;
import com.velazquez.apirestpi.dto.CredencialesDTO;
import com.velazquez.apirestpi.models.Usuario;
import com.velazquez.apirestpi.services.CredentialsService;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private ConsumidorServiceImpl consumidorService;

    @Autowired
    private OfertanteServiceImpl ofertanteService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private final JWTProvider jwtProvider;
    
    public CredentialsServiceImpl() {
        this.jwtProvider = new JWTProvider();
        }   
        
    /*
    @Override
    public boolean registroConsumidor(Consumidor consumidor) {
        Boolean todoOk = false;

        try{
            if(consumidor.getUsuario() == null){
                usuarioService.insertUsuario(consumidor.getUsuario());
                consumidorService.insertConsumidor(consumidor);
                todoOk = true;
           } else {
                consumidorService.insertConsumidor(consumidor);
           }
        }
        catch(Exception e){
            todoOk = false;
        }
        return todoOk;
    }

    @Override
    public boolean registroOfertante(Ofertante ofertante) {
       Boolean todoOk = false;

       try{
           if(ofertante.getUsuario() == null){
               usuarioService.insertUsuario(ofertante.getUsuario());
               ofertanteService.insertOfertante(ofertante);
          } else {
               ofertanteService.insertOfertante(ofertante);
          }
          todoOk = true;
       }
       catch(Exception e){
            todoOk = false;
        }
        return todoOk;
    }
     
     */

    @Override
    public String crearJWTLogin(CredencialesDTO creds) {
        String jwt = "";
        Optional<Usuario> usuario = usuarioService.getUsuarioByUsername(creds.getUsuario());
        if(usuario.isPresent()){
            /*
            if(MainSecurityConfiguration.getPasswordEncoder().matches(creds.getContrasenya(), usuario.get().getContrasenya())){
                jwt = jwtProvider.crearToken(usuario.get());
            } 
             */
            if(usuario.get().getContrasenya().equals(creds.getContrasenya())){
                jwt = jwtProvider.crearToken(usuario.get());
            }
        }
        return jwt;
    }

}
