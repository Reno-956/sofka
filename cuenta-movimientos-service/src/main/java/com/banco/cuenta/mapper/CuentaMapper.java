package com.banco.cuenta.mapper;

import com.banco.cuenta.dto.CuentaDTO;
import com.banco.cuenta.entity.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    CuentaDTO toDTO(Cuenta entity);

    Cuenta toEntity(CuentaDTO dto);
}
