package com.banco.cliente.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCrearRequest {
    private PersonaDTO persona;
    private String contrasena;
}
