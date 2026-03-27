package com.mynorsanchez.kinalapp.service;


import com.mynorsanchez.kinalapp.entity.Venta;
import com.mynorsanchez.kinalapp.repository.VentaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarTodo() {
        return ventaRepository.findAll();
    }

    @Override
    public List<Venta> listarActivos() {
        return ventaRepository.findByEstado(1L);
    }

    @Override
    public Venta guardar(Venta venta) {
        validarVenta(venta);
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorCodigoVenta(Long codigoVenta) {
        return ventaRepository.findById(codigoVenta);
    }

    @Override
    public Venta actualizar(Long codigoVenta, Venta venta) {
        if (!ventaRepository.existsById(codigoVenta)) {
            throw new RuntimeException("La venta no se encontro con el codigoVenta" + codigoVenta);
        }
        venta.setCodigoVenta(codigoVenta);
        validarVenta(venta);
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long codigoVenta) {
        if (!ventaRepository.existsById(codigoVenta)) {
            throw new RuntimeException("La venta no se encontro con el codigoVenta" + codigoVenta);
        }
        ventaRepository.deleteById(codigoVenta);
    }

    @Override
    public boolean existePorCodigoVenta(Long codigoVenta) {
        return ventaRepository.existsById(codigoVenta);
    }

    private void validarVenta(Venta venta) {
        if (venta.getCodigoVenta() == null) {
            throw new IllegalArgumentException("El Codigo Venta es un dato obligatorio");
        }
        if (venta.getFechaVenta() == null) {
            throw new IllegalArgumentException("El Codigo venta es un dato obligatorio");
        }
        if (venta.getTotal() == null) {
            throw new IllegalArgumentException("El Codigo venta es un dato Obligatorio");

        }
        if (venta.getEstado() == null) {
            throw new IllegalArgumentException("El Codigo Venta es un dato Obligatorio");
        }
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("El Codigo venta es un dato Obligatorio");
        }
        if (venta.getUsuario() == null) {
            throw new IllegalArgumentException("El codigo es un dato Obligatorio");
        }

    }
}