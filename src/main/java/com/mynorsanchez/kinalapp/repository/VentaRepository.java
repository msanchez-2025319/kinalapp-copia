package com.mynorsanchez.kinalapp.repository;

import com.mynorsanchez.kinalapp.entity.Venta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public interface VentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findByEstado(Long estado);
}
