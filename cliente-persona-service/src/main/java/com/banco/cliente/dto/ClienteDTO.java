package com.banco.cliente.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;
    private String contrasena;
    private Boolean estado;

    private PersonaDTO persona;
}
