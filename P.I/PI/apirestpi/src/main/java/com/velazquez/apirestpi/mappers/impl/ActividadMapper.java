package com.velazquez.apirestpi.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.velazquez.apirestpi.dto.ActividadDTO;
import com.velazquez.apirestpi.mappers.Mapper;
import com.velazquez.apirestpi.models.Actividad;

@Component
public class ActividadMapper implements Mapper<Actividad, ActividadDTO>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ActividadDTO mapTo(Actividad act) {
        return modelMapper.map(act, ActividadDTO.class);
    }

    @Override
    public Actividad mapFrom(ActividadDTO actDto) {
        return modelMapper.map(actDto, Actividad.class);
    }

    

}
