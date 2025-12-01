package com.banco.cuenta.service;

import com.banco.cuenta.dto.CuentaDTO;
import com.banco.cuenta.dto.MovimientoDTO;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.entity.Movimiento;
import com.banco.cuenta.exception.SaldoNoDisponibleException;
import com.banco.cuenta.mapper.MovimientoMapper;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper mapper;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, MovimientoMapper mapper, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.mapper = mapper;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public MovimientoDTO crear(MovimientoDTO dto) {

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(dto.getNumeroCuenta());
        if (cuenta == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }

        double saldoActual = cuenta.getSaldoInicial();
        double valor = dto.getValor();

        double nuevoSaldo = saldoActual + valor;

        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = mapper.toEntity(dto);

        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(LocalDateTime.now());

        Movimiento guardado = movimientoRepository.save(movimiento);

        return mapper.toDTO(guardado);
    }

    public List<MovimientoDTO> obtenerPorCuenta(String numeroCuenta) {
        return movimientoRepository.findByNumeroCuenta(numeroCuenta)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<MovimientoDTO> obtenerPorRangoFechas(String numeroCuenta, LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByNumeroCuentaAndFechaBetween(numeroCuenta, inicio, fin)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<MovimientoDTO> listar() {
        return movimientoRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public MovimientoDTO actualizar(Long id, MovimientoDTO dto) {
        Movimiento movimiento = mapper.toEntity(dto);
        Movimiento saved = movimientoRepository.save(movimiento);
        return mapper.toDTO(saved);
    }

    public void eliminar(Long id) {
        movimientoRepository.deleteById(id);
    }

}
