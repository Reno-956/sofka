-- Crear schema dbo
CREATE SCHEMA IF NOT EXISTS dbo;


-- ==========================
-- TABLAS
-- ==========================

CREATE TABLE IF NOT EXISTS dbo.persona (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20),
    edad INT,
    identificacion VARCHAR(20) UNIQUE,
    direccion VARCHAR(200),
    telefono VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS dbo.cliente (
    id BIGSERIAL PRIMARY KEY,
    contrasena VARCHAR(100) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    persona_id BIGINT,
    FOREIGN KEY (persona_id) REFERENCES dbo.persona(id)
);

CREATE TABLE IF NOT EXISTS dbo.cuenta (
    id BIGSERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo_inicial NUMERIC(15,2) DEFAULT 0,
    estado BOOLEAN DEFAULT TRUE,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES dbo.cliente(id)
);

CREATE TABLE IF NOT EXISTS dbo.movimiento (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_movimiento VARCHAR(20) NOT NULL,
    valor NUMERIC(15,2) NOT NULL,
    saldo NUMERIC(15,2) NOT NULL,
    numero_cuenta VARCHAR(20),
    FOREIGN KEY (numero_cuenta) REFERENCES dbo.cuenta(numero_cuenta)
);
