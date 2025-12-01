package com.banco.cliente.controller;

import com.banco.cliente.dto.PersonaDTO;
import com.banco.cliente.entity.Persona;
import com.banco.cliente.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping
    public PersonaDTO crear(@RequestBody PersonaDTO dto) {
        return personaService.crear(dto);
    }

    @GetMapping("/{id}")
    public PersonaDTO obtener(@PathVariable Long id) {
        return personaService.obtenerPorId(id);
    }

    @GetMapping
    public List<PersonaDTO> listar() {
        return personaService.listar();
    }

    @PutMapping("/{id}")
    public PersonaDTO actualizar(@PathVariable Long id, @RequestBody PersonaDTO dto) {
        return personaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
    }
}
