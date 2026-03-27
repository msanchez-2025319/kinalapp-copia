package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarTodo();
    List<Producto> listarActivos();

    Producto guardar (Producto producto);

    Optional<Producto> buscarPorCodigoProducto(Long codigoProducto);

    Producto actualizar(Long codigoProducto, Producto producto);

    void eliminar(Long codigoProducto);

    boolean existePorCodigoProducto (Long codigoProducto);

}
