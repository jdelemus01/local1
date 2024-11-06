package com.velazquez.apirestpi.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.velazquez.apirestpi.dto.OfertanteDTO;
import com.velazquez.apirestpi.mappers.Mapper;
import com.velazquez.apirestpi.models.Ofertante;

@Component
public class OfertanteMapper implements Mapper<Ofertante, OfertanteDTO>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OfertanteDTO mapTo(Ofertante ofertante) {
        return modelMapper.map(ofertante, OfertanteDTO.class);
    }

    @Override
    public Ofertante mapFrom(OfertanteDTO ofertanteDTO) {
        return modelMapper.map(ofertanteDTO, Ofertante.class);
    }


    
}
