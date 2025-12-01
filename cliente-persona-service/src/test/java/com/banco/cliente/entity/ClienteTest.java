package com.banco.cliente.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void crearClienteCorrectamente() {

        Persona persona = Persona.builder()
                .id(1L)
                .nombre("Juan Perez")
                .identificacion("1234567890")
                .telefono("0999999999")
                .build();

        Cliente cliente = Cliente.builder()
                .id(10L)
                .contrasena("1234")
                .estado(true)
                .persona(persona)
                .build();

        assertEquals(10L, cliente.getId());
        assertEquals("1234", cliente.getContrasena());
        assertTrue(cliente.getEstado());
        assertEquals("Juan Perez", cliente.getPersona().getNombre());
    }
}
