package com.banco.cuenta.service;

import com.banco.cuenta.dto.CuentaReporteDTO;
import com.banco.cuenta.dto.EstadoCuentaResponse;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.entity.Movimiento;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public ReporteService(CuentaRepository cuentaRepository,
                          MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public EstadoCuentaResponse generarReporte(Long clienteId,
                                               LocalDateTime inicio,
                                               LocalDateTime fin) {

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        EstadoCuentaResponse response = new EstadoCuentaResponse();
        response.setClienteId(clienteId);

        List<CuentaReporteDTO> cuentasDTO = cuentas.stream().map(cuenta -> {

            List<Movimiento> movimientos = movimientoRepository
                    .findByNumeroCuentaAndFechaBetween(cuenta.getNumeroCuenta(), inicio, fin);

            return new CuentaReporteDTO(
                    cuenta.getNumeroCuenta(),
                    cuenta.getSaldoInicial(),
                    movimientos
            );

        }).toList();

        response.setCuentas(cuentasDTO);

        return response;
    }
}

