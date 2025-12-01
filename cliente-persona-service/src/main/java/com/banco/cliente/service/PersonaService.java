package com.banco.cliente.service;

import com.banco.cliente.dto.PersonaDTO;
import com.banco.cliente.entity.Persona;
import com.banco.cliente.mapper.PersonaMapper;
import com.banco.cliente.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    public PersonaDTO crear(PersonaDTO dto) {
        Persona persona = personaMapper.toEntity(dto);
        Persona saved = personaRepository.save(persona);
        return personaMapper.toDTO(saved);
    }

    public PersonaDTO obtenerPorId(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return personaMapper.toDTO(persona);
    }

    public List<PersonaDTO> listar() {
        return personaRepository.findAll()
                .stream()
                .map(personaMapper::toDTO)
                .toList();
    }

    public PersonaDTO actualizar(Long id, PersonaDTO dto) {
        Persona persona = personaMapper.toEntity(dto);
        persona.setId(id);
        Persona saved = personaRepository.save(persona);
        return personaMapper.toDTO(saved);
    }

    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }
}
