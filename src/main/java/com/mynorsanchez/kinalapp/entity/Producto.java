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
    private String codigoCliente;
    @Column
    private  String nombre_producto;
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;
    @Column
    private int stock;
    @Column
    private int estado;

    public Producto(){

    }

    public Producto(String codigoCliente, String nombre_producto, BigDecimal precio, int stock, int estado) {
        this.codigoCliente = codigoCliente;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }
    
}
