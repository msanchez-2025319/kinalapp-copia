package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
List<Usuario> listarTodo();
List<Usuario> listarActivos();

}
