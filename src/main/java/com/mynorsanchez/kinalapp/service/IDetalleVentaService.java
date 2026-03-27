package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Producto;

import java.util.List;

public interface IDetalleVentaService {

    List<Producto> listarTodo();
    List<Producto> listarActivos();

    Producto guardar (Producto producto);

}
