package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.service.IDetalleVentaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleVentas")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        List<DetalleVenta> detalleVentas = detalleVentaService.listarTodo();
        if (detalleVentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalleVentas);
    }

    @GetMapping("/{codigoDetalleVenta}")
    public ResponseEntity<DetalleVenta> buscarPorCodigoDetalleVenta(@PathVariable Long codigoDetalleVenta) {
        return detalleVentaService.buscarPorCodigoDetalleVenta(codigoDetalleVenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta nuevoDetalleVenta = detalleVentaService.guardar(detalleVenta);
            return new ResponseEntity<>(nuevoDetalleVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error de integridad: datos duplicados o referencia inválida.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar el detalle de venta.");
        }
    }

    @DeleteMapping("/{codigoDetalleVenta}")
    public ResponseEntity<?> eliminar(@PathVariable Long codigoDetalleVenta) {
        try {
            if (!detalleVentaService.existePorCodigoDetalleVenta(codigoDetalleVenta)) {
                return ResponseEntity.notFound().build();
            }
            detalleVentaService.eliminar(codigoDetalleVenta);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar: el detalle tiene registros relacionados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al eliminar el detalle de venta.");
        }
    }

    @PutMapping("/{codigoDetalleVenta}")
    public ResponseEntity<?> actualizar(@PathVariable Long codigoDetalleVenta, @RequestBody DetalleVenta detalleVenta) {
        try {
            if (!detalleVentaService.existePorCodigoDetalleVenta(codigoDetalleVenta)) {
                return ResponseEntity.notFound().build();
            }
            DetalleVenta detalleVentaActualizado = detalleVentaService.actualizar(codigoDetalleVenta, detalleVenta);
            return ResponseEntity.ok(detalleVentaActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error de integridad al actualizar el detalle de venta.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar el detalle de venta.");
        }
    }
}