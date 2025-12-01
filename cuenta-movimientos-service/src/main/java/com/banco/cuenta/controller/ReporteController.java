package com.banco.cuenta.controller;

import com.banco.cuenta.dto.EstadoCuentaResponse;
import com.banco.cuenta.service.ReporteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public EstadoCuentaResponse generarReporte(
            @RequestParam Long clienteId,
            @RequestParam String inicio,
            @RequestParam String fin) {

        return reporteService.generarReporte(
                clienteId,
                LocalDateTime.parse(inicio),
                LocalDateTime.parse(fin)
        );
    }
}

