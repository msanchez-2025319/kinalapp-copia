package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarTodo() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarPorCodigoDetalleVenta(Long codigoDetalleVenta) {
        return detalleVentaRepository.findById(codigoDetalleVenta);
    }

    @Override
    public DetalleVenta actualizar(Long codigoDetalleVenta, DetalleVenta detalleVenta) {
        if (!detalleVentaRepository.existsById(codigoDetalleVenta)) {
            throw new IllegalArgumentException("Detalle de venta no encontrado con código: " + codigoDetalleVenta);
        }
        detalleVenta.setCodigoDetalleVenta(codigoDetalleVenta);
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminar(Long codigoDetalleVenta) {
        if (!detalleVentaRepository.existsById(codigoDetalleVenta)) {
            throw new IllegalArgumentException("Detalle de venta no encontrado con código: " + codigoDetalleVenta);
        }
        detalleVentaRepository.deleteById(codigoDetalleVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoDetalleVenta(Long codigoDetalleVenta) {
        return detalleVentaRepository.existsById(codigoDetalleVenta);
    }

    private void validarDetalleVenta(DetalleVenta detalleVenta) {
        if (detalleVenta.getCantidad() == null || detalleVenta.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad es obligatoria y debe ser mayor a 0.");
        }
        if (detalleVenta.getPrecioUnitario() == null) {
            throw new IllegalArgumentException("El precio unitario es obligatorio.");
        }
        if (detalleVenta.getSubtotal() == null) {
            throw new IllegalArgumentException("El subtotal es obligatorio.");
        }
        if (detalleVenta.getProducto() == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }
        if (detalleVenta.getVenta() == null) {
            throw new IllegalArgumentException("La venta es obligatoria.");
        }
    }
}