package com.velazquez.apirestpi.controllers.ejemplo;

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

import com.velazquez.apirestpi.dto.ActividadDTO;
import com.velazquez.apirestpi.models.Actividad;
import com.velazquez.apirestpi.services.impl.ActividadServiceImpl;
import com.velazquez.apirestpi.mappers.Mapper;



@RestController
@RequestMapping("/actividad")
@CrossOrigin
public class ActividadController {

    private final Logger log = LoggerFactory.getLogger(ActividadController.class);

    @Autowired
    private ActividadServiceImpl actividadService;

    @Autowired
    private Mapper<Actividad, ActividadDTO> actMapper;

    @GetMapping("/allActividades")
    public List<Actividad> getAllActividades() {
        return actividadService.getAllActividades();
    }

    @GetMapping("/getActividad/{id}")
    public ResponseEntity<Actividad> getActividad(@PathVariable Long id){
        Optional<Actividad> actGet = actividadService.getActividadById(id);

        if(!actGet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(actGet.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/borrarActividad/{id}")
    public ResponseEntity<?> borrarActividad(@PathVariable("id") Long id) {
        actividadService.deleteActividad(id);
        return new ResponseEntity<>(actividadService.getAllActividades(), HttpStatus.OK);
    }

    @PostMapping("insertarActividad")
    public Actividad postMethodName(@RequestBody Actividad actividadIns) {
        return actividadService.insertActividad(actividadIns);
    }
    

    
    /*
    @PutMapping("modificarActividad/{id}")
    public Actividad putMethodName(@PathVariable Long id, @RequestBody ActividadDTO actividadUp) {
        Optional<Actividad> actUpdate = actividadService.getActividadById(id);

        if(actUpdate == null){
            log.error("Esta actividad no está en la base de datos");
            return null;
        } else {
            actUpdate.setId(id);
            actUpdate.setNombre(actividadUp.getNombre());
            actUpdate.setDuracion(actividadUp.getDuracion());
            actUpdate.setFecha(actividadUp.getFecha());
            actUpdate.setPrecio(actividadUp.getPrecio());
            actUpdate.setAireLibre(actividadUp.isAireLibre());
            actUpdate.setMas18(actividadUp.isMas18());
            actUpdate.setOfertante(actividadUp.getOfertante());
            actUpdate.setTipo(actividadUp.getTipo());

            return actividadService.updateActividad(actUpdate); 
        }
    }
     */
}
