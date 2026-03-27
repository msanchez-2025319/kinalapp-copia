package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.entity.Venta;
import com.mynorsanchez.kinalapp.service.IVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Ventas")
public class VentaController {
    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) { this.ventaService = ventaService; }

    @GetMapping
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> ventas = ventaService.listarTodo();
        return ResponseEntity.ok(ventas);
    }
}
