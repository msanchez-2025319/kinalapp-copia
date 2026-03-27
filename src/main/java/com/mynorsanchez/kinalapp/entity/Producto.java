package com.mynorsanchez.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")

public class Producto {
    @Id
    @Column (name = "codigo_producto")
    private Long codigoProducto;
    @Column
    private  String nombre_producto;
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;
    @Column
    private Long stock;
    @Column
    private Long estado;

    public Producto(){

    }

    public Producto(Long codigoProducto, String nombre_producto, BigDecimal precio, Long stock, Long estado) {
        this.codigoProducto = codigoProducto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }
}
