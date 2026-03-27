package com.mynorsanchez.kinalapp.repository;

import com.mynorsanchez.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findByEstado(Long estado);
}
