package com.banco.cuenta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuenta", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", unique = true)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    @Column(name = "saldo_inicial")
    private Double saldoInicial;

    private Boolean estado;

    @Column(name = "cliente_id")
    private Long clienteId;
}
