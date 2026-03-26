package com.mynorsanchez.kinalapp.repository;

import com.mynorsanchez.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository <Usuario,Long> {
}
