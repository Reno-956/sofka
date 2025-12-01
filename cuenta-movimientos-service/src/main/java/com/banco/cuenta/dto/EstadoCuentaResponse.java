package com.banco.cuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCuentaResponse {
    private Long clienteId;
    private List<CuentaReporteDTO> cuentas;
}

