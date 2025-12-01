package com.banco.cuenta.mapper;

import com.banco.cuenta.dto.MovimientoDTO;
import com.banco.cuenta.entity.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);

    @Mapping(target = "fecha", expression = "java(entity.getFecha().toString())")
    MovimientoDTO toDTO(Movimiento entity);

    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.parse(dto.getFecha()))")
    Movimiento toEntity(MovimientoDTO dto);
}
