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
    @Transactional(readOnly = true)
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
            throw new IllegalArgumentException("No se encontró la venta con código: " + codigoVenta);
        }
        venta.setCodigoVenta(codigoVenta);
        validarVenta(venta);
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long codigoVenta) {
        if (!ventaRepository.existsById(codigoVenta)) {
            throw new IllegalArgumentException("No se encontró la venta con código: " + codigoVenta);
        }
        ventaRepository.deleteById(codigoVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoVenta(Long codigoVenta) {
        return ventaRepository.existsById(codigoVenta);
    }

    private void validarVenta(Venta venta) {
        if (venta.getFechaVenta() == null) {
            throw new IllegalArgumentException("La fecha de venta es obligatoria.");
        }
        if (venta.getEstado() == null) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio.");
        }
        if (venta.getUsuario() == null) {
            throw new IllegalArgumentException("El usuario es obligatorio.");
        }
    }
}