package com.banco.cliente.controller;

import com.banco.cliente.dto.ClienteCrearRequest;
import com.banco.cliente.dto.ClienteDTO;
import com.banco.cliente.entity.Cliente;
import com.banco.cliente.entity.Persona;
import com.banco.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteDTO crear(@RequestBody ClienteCrearRequest request) {
        return clienteService.crearCliente(request.getPersona(), request.getContrasena());
    }

    @GetMapping("/{id}")
    public ClienteDTO obtener(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @PutMapping("/{id}")
    public ClienteDTO actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return clienteService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Cliente y persona asociada eliminados correctamente");
        respuesta.put("idEliminado", id);

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/login")
    public ClienteDTO login(@RequestBody LoginRequest request) {
        return clienteService.login(request.getIdentificacion(), request.getContrasena());
    }
}
