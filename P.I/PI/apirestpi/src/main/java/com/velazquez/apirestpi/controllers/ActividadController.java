package com.velazquez.apirestpi.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.models.Actividad;
import com.velazquez.apirestpi.services.impl.ActividadServiceImpl;
import com.velazquez.apirestpi.services.impl.OfertanteServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/actividad")
@CrossOrigin
public class ActividadController {

    private final Logger log = LoggerFactory.getLogger(ActividadController.class);

    @Autowired
    private ActividadServiceImpl actividadService;

    @Autowired
    private OfertanteServiceImpl ofertanteService;

    @GetMapping("/allActividades")
    public List<Actividad> getAllActividades() {
        log.info("Cargando todas las actividades...");
        return actividadService.getAllActividades();
    }

    @GetMapping("/getActsByOf/{id}")
    public ResponseEntity<List<Actividad>> getActvidadesByOfer(@PathVariable Long id) {
        if(id != null && ofertanteService.getOfertanteById(id).isPresent()){
            return new ResponseEntity<>(actividadService.getActividadesByOfertante(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/getActividad/{id}")
    public ResponseEntity<Actividad> getActividad(@PathVariable Long id){
        log.info("Cargando la actividad " + id);
        Optional<Actividad> actGet = actividadService.getActividadById(id);

        if(!actGet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(actGet.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/borrarActividad/{id}")
    public ResponseEntity<?> borrarActividad(@PathVariable("id") Long id) {
        log.info("Borrando la actividad " + id);
        actividadService.deleteActividad(id);
        return new ResponseEntity<>(actividadService.getAllActividades(), HttpStatus.OK);
    }

    @PostMapping("insertarActividad")
    public ResponseEntity<?> postMethodName(@RequestBody Actividad actividadIns) {
        log.info("Generando una nueva actividad: " +actividadIns.getNombre());
        if(actividadService.getActividadById(actividadIns.getId()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            actividadService.insertActividad(actividadIns);
            return new ResponseEntity<>(actividadService.getActividadesByOfertante(actividadIns.getOfertante().getId()), HttpStatus.OK);
        }
    }

    @PutMapping("modificarActividad/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody Actividad actividad) {
        Optional<Actividad> actGet = actividadService.getActividadById(id);
        if(!actGet.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            actividadService.updateActividad(actividad);
            return new ResponseEntity<>(actividadService.getActividadesByOfertante(actividad.getOfertante().getId()), HttpStatus.OK);
        }
    }
}
