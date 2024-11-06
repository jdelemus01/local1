package com.velazquez.apirestpi.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.velazquez.apirestpi.dto.TipoDTO;
import com.velazquez.apirestpi.mappers.Mapper;
import com.velazquez.apirestpi.models.Tipo;

@Component
public class TipoMapper implements Mapper<Tipo, TipoDTO>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TipoDTO mapTo(Tipo tipo) {
        return modelMapper.map(tipo, TipoDTO.class);
    }

    @Override
    public Tipo mapFrom(TipoDTO tipoDTO) {
        return modelMapper.map(tipoDTO, Tipo.class);
    }
}
