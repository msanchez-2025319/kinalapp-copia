package com.mynorsanchez.kinalapp.controller;


import com.mynorsanchez.kinalapp.entity.Producto;
import com.mynorsanchez.kinalapp.service.IProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = productoService.listarTodo();
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/{codigoProducto}")
    public ResponseEntity<Producto> buscarPorCodigoProducto(@PathVariable Long codigoProducto){
        return productoService.buscarPorCodigoProducto(codigoProducto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
