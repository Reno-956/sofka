package com.banco.cliente.mapper;

import com.banco.cliente.dto.PersonaDTO;
import com.banco.cliente.entity.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    PersonaDTO toDTO(Persona persona);
    Persona toEntity(PersonaDTO personaDTO);
}
