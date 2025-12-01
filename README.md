# Microservicio de Gestión Bancaria  
Cliente – Persona – Cuenta – Movimiento – Reportes

Este proyecto implementa un sistema bancario básico compuesto por dos microservicios:

- **cliente-persona-service** → Maneja clientes y personas  
- **cuenta-movimiento-service** → Maneja cuentas, movimientos y reportes  

El objetivo es cubrir los requerimientos típicos de un sistema bancario:

- Crear clientes y personas
- Registrar cuentas bancarias
- Registrar movimientos (depósitos/retiros)
- Validar saldos disponibles
- Generar reportes por fechas y cliente
- Registrar auditoría básica de movimientos

---

## **Tecnologías utilizadas**

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **H2 (Tests)**
- **SQL Server (Producción)**
- **MapStruct**
- **Lombok**
- **Maven**

---


## **Características principales**

### CRUD completo
- **Cliente**
- **Persona**
- **Cuenta**
- **Movimiento**

### Registro de movimientos con reglas
- Valores positivos ➝ depósito  
- Valores negativos ➝ retiro  
- Validación automática de saldos  
- Alerta: **"Saldo no disponible"**  
- Actualización automática del saldo en la cuenta

## Empezar 

Para poner en funcionamiento una copia local siga estos pasos. 

1. Clonar el repositorio:
   ```sh
     git clone https://github.com/Reno-956/sofka.git
    ```
2. Levantar todos los servicios usando Docker Compose:  
    ```sh
     docker-compose up --build
    ```
