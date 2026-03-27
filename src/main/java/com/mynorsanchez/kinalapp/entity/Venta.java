package com.mynorsanchez.kinalapp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Venta")
public class Venta {
    @Id
    @Column (name = "codigo_venta")
    private Long codigoVenta;

    @Column
    private LocalDate fechaVenta;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column
    private Long estado;

    /*las llaves foráneas no llevan long*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Clientes_dpi_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_codigo_usuario")
    private Usuario usuario;

    public Venta(){

    }

    public Venta(Long codigoVenta, LocalDate fechaVenta, BigDecimal total, Long estado, Cliente cliente, Usuario usuario) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }
}
