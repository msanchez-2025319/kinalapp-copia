package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
List<Usuario> listarTodo();
List<Usuario> listarActivos();

Usuario guardar (Usuario usuario);

Optional<Usuario> buscarPorCodigoU(Long codigoU);

Usuario actualizar(Long codigoU, Usuario usuario);

void eliminar(Long codigoU);

boolean existePorCodigoU (Long codigoU);
}

