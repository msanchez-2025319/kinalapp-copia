package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Venta;

import java.util.List;

public interface IVentaService {
    List<Venta> listarTodo();
    List<Venta> listarActivos();

    Venta guardar (Venta venta);
        
}
