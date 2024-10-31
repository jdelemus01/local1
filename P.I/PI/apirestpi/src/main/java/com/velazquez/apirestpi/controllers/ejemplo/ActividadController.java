package com.velazquez.apirestpi.controllers.ejemplo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velazquez.apirestpi.models.Actividad;
import com.velazquez.apirestpi.services.impl.ActividadServiceImpl;

@RestController
@RequestMapping("/actividad")
@CrossOrigin
public class ActividadController {

    @Autowired
    private ActividadServiceImpl actividadService;

    @GetMapping("/allActividades")
    public List<Actividad> getAllActividades() {
        return actividadService.getAllActividades();
    }

    @DeleteMapping("/borrarActividad/{id}")
    public ResponseEntity<?> borrarActividad(@PathVariable("id") Long id) {
        actividadService.deleteActividad(id);
        return new ResponseEntity<>(actividadService.getAllActividades(), HttpStatus.OK);

    }

}
