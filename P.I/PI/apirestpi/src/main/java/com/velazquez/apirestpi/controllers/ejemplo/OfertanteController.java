package com.velazquez.apirestpi.controllers.ejemplo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.models.Ofertante;
import com.velazquez.apirestpi.services.impl.OfertanteServiceImpl;



@RestController
@RequestMapping("/ofertante")
@CrossOrigin
public class OfertanteController {

    @Autowired
    OfertanteServiceImpl ofertanteServiceImpl;

    @GetMapping("/allOfertantes")
    public List<Ofertante> getAllOfertantes() {
        return ofertanteServiceImpl.getAllOfertantes();
    }
    
    @GetMapping("/getOfertante/{id}")
    public ResponseEntity<Ofertante> getOfertante(@PathVariable Long id) {
        Optional<Ofertante> ofertanteGet = ofertanteServiceImpl.getOfertanteById(id);

        if(ofertanteGet.isPresent()){
            return new ResponseEntity<>(ofertanteGet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
