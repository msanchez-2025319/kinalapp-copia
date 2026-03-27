package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarTodo();
    List<DetalleVenta> listarActivos();

    DetalleVenta guardar (DetalleVenta detalleVenta);

    Optional<DetalleVenta> buscarPorCodigoDetalleVenta (Long codigoDetalleVenta);

    DetalleVenta actualizar(Long codigoDetalleVenta, DetalleVenta detalleVenta);

    void eliminar(Long codigoDetalleVenta);

    boolean existePorCodigoDetalleVenta(Long codigoDetalleVenta);
}
