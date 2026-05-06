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
    public List<Usuario> listarTodo() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarActivos() {
        return usuarioRepository.findByEstado(1L);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuario.setCodigoUsuario(null);
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorCodigoU(Long codigoU) {
        return usuarioRepository.findById(codigoU);
    }

    @Override
    public Usuario actualizar(Long codigoU, Usuario usuario) {
        if (!usuarioRepository.existsById(codigoU)) {
            throw new IllegalArgumentException("Usuario no encontrado con codigo: " + codigoU);
        }
        usuario.setCodigoUsuario(codigoU);
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long codigoU) {
        if (!usuarioRepository.existsById(codigoU)) {
            throw new IllegalArgumentException("Usuario no encontrado con codigo: " + codigoU);
        }
        usuarioRepository.deleteById(codigoU);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoU(Long codigoU) {
        return usuarioRepository.existsById(codigoU);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("El username es obligatorio.");
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("El password es obligatorio.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }
        if (usuario.getEstado() == null) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }
    }
}