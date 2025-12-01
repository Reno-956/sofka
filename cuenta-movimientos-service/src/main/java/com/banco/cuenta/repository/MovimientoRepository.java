package com.banco.cuenta.repository;

import com.banco.cuenta.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByNumeroCuenta(String numeroCuenta);

    List<Movimiento> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Movimiento> findByNumeroCuentaAndFechaBetween(
            String numeroCuenta,
            LocalDateTime inicio,
            LocalDateTime fin
    );
}
