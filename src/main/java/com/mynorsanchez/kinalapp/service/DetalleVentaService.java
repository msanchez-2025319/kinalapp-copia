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
    @Transactional
    public List<DetalleVenta> listarTodo() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public List<DetalleVenta> listarActivos() {
        return detalleVentaRepository.findByEstado(1L);
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
        if (!detalleVentaRepository.existsById(codigoDetalleVenta)){
            throw new RuntimeException("El Detalle de venta no se encontro con el codigoDetalleVenta"+codigoDetalleVenta);
        }
        detalleVenta.setCodigoDetalleVenta(codigoDetalleVenta);
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);

    }

    @Override
    public void eliminar(Long codigoDetalleVenta) {
        if (!detalleVentaRepository.existsById(codigoDetalleVenta)){
            throw new RuntimeException("El Detalle de venta no se encontro con el codigoDetalleVenta"+codigoDetalleVenta);
        }
        detalleVentaRepository.deleteById(codigoDetalleVenta);
    }

    @Override
    public boolean existePorCodigoDetalleVenta(Long codigoDetalleVenta) {
        return detalleVentaRepository.existsById(codigoDetalleVenta);
    }
    private void validarDetalleVenta(DetalleVenta detalleVenta){
        if (detalleVenta.getCodigoDetalleVenta()== null){
            throw new IllegalArgumentException("El Codigo validar venta es un dato obligatorio");
        }
        if (detalleVenta.getCantidad()== null){
            throw new IllegalArgumentException("La cantidad es un dato obligatorio");
        }
        if (detalleVenta.getPrecioUnitario()== null) {
            throw new IllegalArgumentException("La Precio unitario es un dato Obligatorio");
        }
        if (detalleVenta.getSubtotal()== null){
            throw new IllegalArgumentException("El subtotal es un dato obligatorio");
        }
        if (detalleVenta.getProducto()== null){
            throw new IllegalArgumentException("El Producto es un dato obligatorio");
        }
        if (detalleVenta.getVenta()== null){
            throw new IllegalArgumentException("La venta es un dato obligatorio");
        }
    }
}
