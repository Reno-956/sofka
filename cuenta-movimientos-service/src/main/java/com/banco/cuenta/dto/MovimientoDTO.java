package com.banco.cuenta.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private Long id;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private String numeroCuenta;
    private String fecha;
}
