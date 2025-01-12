package com.velazquez.apirestpi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velazquez.apirestpi.config.JWTProvider;
import com.velazquez.apirestpi.config.MainSecurityConfiguration;
import com.velazquez.apirestpi.models.Consumidor;
import com.velazquez.apirestpi.models.Ofertante;
import com.velazquez.apirestpi.services.CredentialsService;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private ConsumidorServiceImpl consumidorService;

    @Autowired
    private OfertanteServiceImpl ofertanteService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    private final JWTProvider jwtProvider;

    public CredentialsServiceImpl() {
        this.jwtProvider = new JWTProvider();
    }   

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

    @Override
    public String loginConsumidor(String email, String contrasenya) {
        String jwt = "";
        Optional<Consumidor> consumidor = consumidorService.getConsumidorByEmail(email);

        if(consumidor.isPresent()){            
            if(MainSecurityConfiguration.getPasswordEncoder().matches(contrasenya, consumidor.get().getContrasenya())){
                jwt = jwtProvider.crearToken(consumidor.get().getUsuario(), "CON");
            }
        }
        return jwt;
    }

    @Override
    public String loginOfertante(String email, String contrasenya) {
        // TODO Auto-generated method stub
        String jwt = "";
        Optional<Ofertante> ofertante = ofertanteService.getOfertanteByEmail(email);

        if(ofertante.isPresent()){            
            if(MainSecurityConfiguration.getPasswordEncoder().matches(contrasenya, ofertante.get().getContrasenya())){
                jwt = jwtProvider.crearToken(ofertante.get().getUsuario(), "CON");
            }
        }
        return jwt;
    }

}
