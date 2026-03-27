package com.mynorsanchez.kinalapp.repository;

import com.mynorsanchez.kinalapp.entity.Cliente;
import com.mynorsanchez.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository <Usuario,Long> {
    List<Usuario> findByEstado(Long estado);
}
