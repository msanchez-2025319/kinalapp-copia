package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Venta;
import com.mynorsanchez.kinalapp.service.IVentaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        List<Venta> ventas = ventaService.listarTodo();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{codigoVenta}")
    public ResponseEntity<Venta> buscarPorCodigoVenta(@PathVariable Long codigoVenta) {
        return ventaService.buscarPorCodigoVenta(codigoVenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.guardar(venta);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error de integridad: datos duplicados o inválidos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar la venta.");
        }
    }

    @DeleteMapping("/{codigoVenta}")
    public ResponseEntity<?> eliminar(@PathVariable Long codigoVenta) {
        try {
            if (!ventaService.existePorCodigoVenta(codigoVenta)) {
                return ResponseEntity.notFound().build();
            }
            ventaService.eliminar(codigoVenta);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar: la venta tiene registros relacionados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al eliminar la venta.");
        }
    }

    @PutMapping("/{codigoVenta}")
    public ResponseEntity<?> actualizar(@PathVariable Long codigoVenta, @RequestBody Venta venta) {
        try {
            if (!ventaService.existePorCodigoVenta(codigoVenta)) {
                return ResponseEntity.notFound().build();
            }
            Venta ventaActualizada = ventaService.actualizar(codigoVenta, venta);
            return ResponseEntity.ok(ventaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error de integridad al actualizar la venta.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar la venta.");
        }
    }
}