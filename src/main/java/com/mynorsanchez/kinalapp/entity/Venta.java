package com.mynorsanchez.kinalapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Long codigoVenta;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private Long estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Clientes_dpi_cliente", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_codigo_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"venta", "hibernateLazyInitializer", "handler"})
    private List<DetalleVenta> detallesVenta = new ArrayList<>();

    public Venta() {
        this.fechaVenta = LocalDate.now();
        this.estado = 1L;
        this.total = BigDecimal.ZERO;
    }

    // Método helper para sincronizar ambos lados de la relación
    public void agregarDetalle(DetalleVenta detalle) {
        detallesVenta.add(detalle);
        detalle.setVenta(this);
    }

    public void eliminarDetalle(DetalleVenta detalle) {
        detallesVenta.remove(detalle);
        detalle.setVenta(null);
    }

    // Calcula el total sumando subtotales de los detalles
    public void calcularTotal() {
        this.total = detallesVenta.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getCodigoVenta() { return codigoVenta; }
    public void setCodigoVenta(Long codigoVenta) { this.codigoVenta = codigoVenta; }

    public LocalDate getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDate fechaVenta) { this.fechaVenta = fechaVenta; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Long getEstado() { return estado; }
    public void setEstado(Long estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<DetalleVenta> getDetallesVenta() { return detallesVenta; }
    public void setDetallesVenta(List<DetalleVenta> detallesVenta) { this.detallesVenta = detallesVenta; }
}