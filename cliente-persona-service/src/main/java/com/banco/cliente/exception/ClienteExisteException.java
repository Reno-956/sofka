package com.banco.cliente.exception;

public class ClienteExisteException extends RuntimeException {
    public ClienteExisteException(String message) {
        super(message);
    }
}
