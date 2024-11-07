package com.velazquez.apirestpi.controllers;

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

import com.velazquez.apirestpi.dto.TipoDTO;
import com.velazquez.apirestpi.models.Tipo;
import com.velazquez.apirestpi.services.impl.TipoServiceImpl;


@RestController
@RequestMapping("/tipo")
@CrossOrigin
public class TipoController {

    @Autowired
    TipoServiceImpl tipoService;

    @GetMapping("/allTipos")
    public List<Tipo> getMethodName() {
        return tipoService.getAllTipos();
    }

    @GetMapping("/getTipo/{id}")
    public ResponseEntity<TipoDTO> getTipo(@PathVariable Long id) {
        Optional<TipoDTO>  tipoGet = tipoService.getTipoById(id);

        if(tipoGet.isPresent()){
            return new ResponseEntity<>(tipoGet.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
