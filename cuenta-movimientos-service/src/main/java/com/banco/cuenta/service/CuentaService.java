package com.banco.cuenta.service;

import com.banco.cuenta.dto.CuentaDTO;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.exception.CuentaExisteException;
import com.banco.cuenta.mapper.CuentaMapper;
import com.banco.cuenta.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper mapper;

    public CuentaService(CuentaRepository cuentaRepository, CuentaMapper mapper) {
        this.cuentaRepository = cuentaRepository;
        this.mapper = mapper;
    }

    public CuentaDTO crear(CuentaDTO dto) {
        if (cuentaRepository.findByNumeroCuenta(dto.getNumeroCuenta()) != null) {
            throw new CuentaExisteException("La cuenta ya está registrada: "
                    + dto.getNumeroCuenta());
        }
        Cuenta cuenta = mapper.toEntity(dto);
        return mapper.toDTO(cuentaRepository.save(cuenta));
    }

    public CuentaDTO obtenerPorNumeroCuenta(String numeroCuenta) {
        return mapper.toDTO(cuentaRepository.findByNumeroCuenta(numeroCuenta));
    }

    public List<CuentaDTO> obtenerPorClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<CuentaDTO> listar() {
        return cuentaRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional
    public CuentaDTO actualizar(CuentaDTO dto) {

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(dto.getNumeroCuenta());

        if (cuenta == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }

        cuenta.setTipoCuenta(dto.getTipoCuenta());
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        cuenta.setEstado(dto.getEstado());

        cuentaRepository.save(cuenta);

        return mapper.toDTO(cuenta);
    }


    public void eliminar(Long id) {
        cuentaRepository.deleteById(id);
    }
}
