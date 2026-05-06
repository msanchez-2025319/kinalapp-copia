package com.mynorsanchez.kinalapp.repository;

import com.mynorsanchez.kinalapp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository <Producto, Long> {
    List<Producto> findByEstado(Long estado);
}
