package com.mynorsanchez.kinalapp.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {
    @Id
    @Column(name = "codigo_detalle_venta")
   private Long codigoDetalleVenta;

    @Column
    private Long cantidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Producto_codigo_producto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ventas_codigo_venta")
    private  Venta venta;

    public DetalleVenta(){

    }

    public DetalleVenta(Long codigoDetalleVenta, Long cantidad, BigDecimal precioUnitario, BigDecimal subtotal, Producto producto, Venta venta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.producto = producto;
        this.venta = venta;
    }
}
