package com.banco.cliente.service;

import com.banco.cliente.dto.ClienteDTO;
import com.banco.cliente.dto.PersonaDTO;
import com.banco.cliente.entity.Cliente;
import com.banco.cliente.entity.Persona;
import com.banco.cliente.exception.ClienteExisteException;
import com.banco.cliente.exception.RecursoNoEncontradoException;
import com.banco.cliente.mapper.ClienteMapper;
import com.banco.cliente.mapper.PersonaMapper;
import com.banco.cliente.repository.ClienteRepository;
import com.banco.cliente.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;
    private final ClienteMapper clienteMapper;
    private final PersonaMapper personaMapper;

    @Transactional
    public ClienteDTO crearCliente(PersonaDTO personaDTO, String contrasena) {
        if (personaRepository.findByIdentificacion(personaDTO.getIdentificacion()) != null) {
            throw new ClienteExisteException("La identificación ya está registrada: "
                    + personaDTO.getIdentificacion());
        }

        Persona persona = personaMapper.toEntity(personaDTO);
        Persona personaGuardada = personaRepository.save(persona);

        Cliente cliente = Cliente.builder()
                .persona(personaGuardada)
                .contrasena(contrasena)
                .estado(true)
                .build();

        Cliente saved = clienteRepository.save(cliente);
        return clienteMapper.toDTO(saved);
    }

    public ClienteDTO obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    public List<ClienteDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .toList();
    }

    public ClienteDTO actualizar(Long id, ClienteDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.setId(id);
        Cliente saved = clienteRepository.save(cliente);
        return clienteMapper.toDTO(saved);
    }

    @Transactional
    public void eliminar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        Long personaId = cliente.getPersona().getId();

        clienteRepository.delete(cliente);

        personaRepository.deleteById(personaId);
    }


    public ClienteDTO login(String identificacion, String contrasena) {
        Cliente cliente = clienteRepository.findByPersonaIdentificacionAndContrasena(identificacion, contrasena);
        if (cliente == null) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        return clienteMapper.toDTO(cliente);
    }
}
