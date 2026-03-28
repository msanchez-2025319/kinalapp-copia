package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.service.IDetalleVentaService;

import org.springframework.http.HttpStatus;
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
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta){
        try {
            DetalleVenta nuevoDetalleVenta = detalleVentaService.guardar(detalleVenta);
            return new ResponseEntity<>(nuevoDetalleVenta, HttpStatus.CREATED);
        }   catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{codigoDetalleVenta}")
    public ResponseEntity<Void> eliminar (@PathVariable Long codigoDetalleVenta){
        try {
            if (!detalleVentaService.existePorCodigoDetalleVenta(codigoDetalleVenta)){
                return ResponseEntity.notFound().build();
            }
            detalleVentaService.eliminar(codigoDetalleVenta);
            return ResponseEntity.noContent().build();

        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }
}
