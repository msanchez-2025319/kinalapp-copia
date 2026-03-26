package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.service.IClientesService;
import com.mynorsanchez.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usorios")

public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping

    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarTodo();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{codigoU}")
    public ResponseEntity<Usuario> buscarPorCodigoU(@PathVariable Long codigoU) {
        return usuarioService.buscarPorCodigoU(codigoU).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigoU}")
    public ResponseEntity<Void> eliminar(@PathVariable Long codigoU) {
        try {
            if (!usuarioService.existePorCodigoU(codigoU)) {
                return ResponseEntity.notFound().build();
            }
            usuarioService.eliminar(codigoU);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/codigoU")
    public ResponseEntity<?> actualizar(@PathVariable Long codigoU, @RequestBody Usuario usuario) {
        try {
            if (!usuarioService.existePorCodigoU(codigoU)) {
                return ResponseEntity.notFound().build();
            }
            Usuario usuariActualizado = usuarioService.actualizar(codigoU, usuario);
            return ResponseEntity.ok(usuariActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
