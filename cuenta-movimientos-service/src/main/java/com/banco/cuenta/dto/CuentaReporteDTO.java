package com.banco.cuenta.dto;

import com.banco.cuenta.entity.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaReporteDTO {
    private String numeroCuenta;
    private Double saldo;
    private List<Movimiento> movimientos;
}

