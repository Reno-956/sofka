package com.banco.cliente.mapper;

import com.banco.cliente.dto.ClienteDTO;
import com.banco.cliente.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonaMapper.class)
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
}
