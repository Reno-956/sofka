package com.banco.cliente.controller;

import lombok.Data;

@Data
public class LoginRequest {
    private String identificacion;
    private String contrasena;
}
