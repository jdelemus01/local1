package com.velazquez.apirestpi.controllers.ejemplo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
