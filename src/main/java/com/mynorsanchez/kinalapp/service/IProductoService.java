package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarTodo();
    List<Producto> listarActivos();

    Producto guardar (Producto producto);

    Optional<Producto> buscarPorCodigoProducto(String codigoProducto);

    Producto actualizar(String codigoProducto, Producto producto);

    void eliminar(String codigoProducto);

    boolean existePorCodigoProducto (String codigoProducto);

}
