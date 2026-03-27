package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {
    List<Venta> listarTodo();
    List<Venta> listarActivos();

    Venta guardar (Venta venta);

    Optional<Venta> buscarPorCodigoVenta(Long codigoVenta);

    Venta actualizar(Long codigoVenta, Venta venta);

    void eliminar(Long codigoVenta);

    boolean existePorCodigoVenta(Long codigoVenta);
}
