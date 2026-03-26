package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.repository.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodo() { return usuarioRepository.findAll();}

    @Override
    public List<Usuario> listarActivos() { return usuarioRepository.findByEstado(1L);}

    @Override
    public Usuario guardar(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Usuario> buscarPorCodigoU(Long codigoU) {
        return Optional.empty();
    }

    @Override
    public Usuario actualizar(Long codigoU, Usuario usuario) {
        return null;
    }

    @Override
    public void eliminar(Long codigoU) {

    }

    @Override
    public boolean existePorCodigoU(Long codigoU) {
        return false;
    }
}
