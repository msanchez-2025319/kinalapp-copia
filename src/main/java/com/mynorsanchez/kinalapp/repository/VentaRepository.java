package com.mynorsanchez.kinalapp.repository;

import com.mynorsanchez.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByEstado(Long estado);
    List<Venta> findByClienteDPICliente(String dpiCliente);
}