package com.velazquez.apirestpi.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.velazquez.apirestpi.models.ConsumidorActividad;
import com.velazquez.apirestpi.models.ConsumidorActividadId;
import com.velazquez.apirestpi.services.impl.ConsumoActividadServiceImpl;

@RestController
@RequestMapping("/consumoActividad")
@CrossOrigin
public class ConsumoActConsController {

    private final Logger log = LoggerFactory.getLogger(ConsumoActConsController.class);

    @Autowired
    private ConsumoActividadServiceImpl consumoActividadService;

    @GetMapping("/getConsumoByActs/{id}")
    public ResponseEntity<List<ConsumidorActividad>> getConsumoByAct(@PathVariable Long id) {
        log.info("Se ha recibido este id: " + id);
        if (id != null) {
            return new ResponseEntity<>(consumoActividadService.getConsumoActividadByActividad(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getConsumoByCons/{id}")
    public ResponseEntity<List<ConsumidorActividad>> getConsumoByCons(@PathVariable Long id) {
        log.info("Se ha recibido este id: " + id);
        if (id != null) {
            return new ResponseEntity<>(consumoActividadService.getConsumoActividadByConsumidor(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insertConsumo")
    public ResponseEntity<?> insertConsumo(@RequestBody ConsumidorActividad consumo) {
        consumoActividadService.insertConsumo(consumo);

        return new ResponseEntity<List<ConsumidorActividad>>(consumoActividadService.getConsumoActividadByConsumidor(consumo.getConsumidor().getId()), HttpStatus.OK);
    }

    @DeleteMapping("/deleteConsumo/{idAct}/{idCons}")
    public ResponseEntity<?> deleteConsumo(@PathVariable Long idAct, @PathVariable Long idCons) {
        ConsumidorActividadId consId = new ConsumidorActividadId();
        consId.setActividad(idAct);
        consId.setConsumidor(idCons);

        ConsumidorActividad cons = consumoActividadService.getConsumoActividad(consId);
        consumoActividadService.deleteConsumo(cons);
        return new ResponseEntity<List<ConsumidorActividad>>(consumoActividadService.getConsumoActividadByConsumidor(idCons) , HttpStatus.OK);
    }

}
