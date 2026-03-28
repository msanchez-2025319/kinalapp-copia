package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.service.IDetalleVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/detalleVenta")
public class DetalleVentaController {
    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping("/{codigoDetalleVenta}")
    public ResponseEntity<DetalleVenta> buscarPorCodigoDetalleVenta(@PathVariable Long codigoDetalleVenta){
        return detalleVentaService.buscarPorCodigoDetalleVenta(codigoDetalleVenta).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
   
}
