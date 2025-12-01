package com.banco.cuenta.controller;

import com.banco.cuenta.dto.CuentaDTO;
import com.banco.cuenta.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public CuentaDTO crear(@RequestBody CuentaDTO dto) {
        return cuentaService.crear(dto);
    }

    @GetMapping("/{numeroCuenta}")
    public CuentaDTO obtener(@PathVariable String numeroCuenta) {
        return cuentaService.obtenerPorNumeroCuenta(numeroCuenta);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<CuentaDTO> obtenerPorCliente(@PathVariable Long clienteId) {
        return cuentaService.obtenerPorClienteId(clienteId);
    }

    @GetMapping
    public List<CuentaDTO> listar() {
        return cuentaService.listar();
    }

    @PutMapping("/{numeroCuenta}")
    public CuentaDTO actualizar(@PathVariable String numeroCuenta, @RequestBody CuentaDTO dto) {
        dto.setNumeroCuenta(numeroCuenta);
        return cuentaService.actualizar(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        cuentaService.eliminar(id);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Cuenta eliminada correctamente");
        respuesta.put("idEliminado", id);

        return ResponseEntity.ok(respuesta);
    }
}
