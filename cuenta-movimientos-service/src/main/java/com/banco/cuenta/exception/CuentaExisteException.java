package com.banco.cuenta.exception;

public class CuentaExisteException extends RuntimeException {
    public CuentaExisteException(String message) {
        super(message);
    }
}