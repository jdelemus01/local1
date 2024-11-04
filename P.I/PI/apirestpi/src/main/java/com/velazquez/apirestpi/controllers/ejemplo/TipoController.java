package com.velazquez.apirestpi.controllers.ejemplo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.models.Tipo;
import com.velazquez.apirestpi.services.impl.TipoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/tipo")
@CrossOrigin
public class TipoController {

    @Autowired
    TipoServiceImpl tipoServiceImpl;

    @GetMapping("/allTipos")
    public List<Tipo> getMethodName() {
        return tipoServiceImpl.getAllTipos();
    }
    
}
