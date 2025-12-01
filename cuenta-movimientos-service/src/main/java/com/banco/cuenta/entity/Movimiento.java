package com.banco.cuenta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movimiento", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoMovimiento;

    private Double valor;

    private Double saldo;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    private java.time.LocalDateTime fecha;
}
