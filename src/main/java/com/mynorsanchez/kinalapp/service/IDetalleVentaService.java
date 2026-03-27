package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<Producto> listarTodo();
    List<Producto> listarActivos();

    Producto guardar (Producto producto);

    Optional<Producto> buscarPorCodigoDetalleVenta (Long codigoDetalleVenta);

    Producto actualizar(Long codigoDetalleVenta, DetalleVenta detalleVenta);

    void eliminar(Long codigoDetalleVenta);

    boolean existePorCodigoDetalleVenta(Long codigoDetalleVenta);
}
