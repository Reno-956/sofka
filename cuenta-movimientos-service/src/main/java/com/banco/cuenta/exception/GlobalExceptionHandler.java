package com.banco.cuenta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private record ErrorResponse(LocalDateTime timestamp, String error, String message) {}

    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<ErrorResponse> handleCuentaExists(SaldoNoDisponibleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), "Saldo no disponible", ex.getMessage()));
    }

    @ExceptionHandler(CuentaExisteException.class)
    public ResponseEntity<ErrorResponse> handleCuentaExists(CuentaExisteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), "Cuenta ya existe", ex.getMessage()));
    }
}
