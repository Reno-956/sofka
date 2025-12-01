package com.banco.cuenta.controller;

import com.banco.cuenta.dto.MovimientoDTO;
import com.banco.cuenta.service.MovimientoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public MovimientoDTO crear(@RequestBody MovimientoDTO dto) {
        return movimientoService.crear(dto);
    }

    @GetMapping("/cuenta/{numeroCuenta}")
    public List<MovimientoDTO> obtenerPorCuenta(@PathVariable String numeroCuenta) {
        return movimientoService.obtenerPorCuenta(numeroCuenta);
    }

    @GetMapping("/cuenta/{numeroCuenta}/rango")
    public List<MovimientoDTO> rango(
            @PathVariable String numeroCuenta,
            @RequestParam String inicio,
            @RequestParam String fin
    ) {
        return movimientoService.obtenerPorRangoFechas(
                numeroCuenta,
                LocalDateTime.parse(inicio),
                LocalDateTime.parse(fin)
        );
    }

    @GetMapping
    public List<MovimientoDTO> listar() {
        return movimientoService.listar();
    }

    @PutMapping("/{id}")
    public MovimientoDTO actualizar(@PathVariable Long id, @RequestBody MovimientoDTO dto) {
        return movimientoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
    }
}
