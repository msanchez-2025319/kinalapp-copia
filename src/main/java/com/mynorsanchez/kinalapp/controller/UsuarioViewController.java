package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/usuario")
public class UsuarioViewController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("usuario", usuarioService.listarTodo());
        return "usuario/listar";
    }
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model){
        model.addAttribute("usuario", new Usuario());
        return "usuario/formulario";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario){
        usuarioService.guardar(usuario);
        return "redirect:/vista/usuario";
    }
    @GetMapping("/editar/{codigo}")
    public String formularioEditar(@PathVariable Long codigo, Model model){
        Usuario usuario = usuarioService.buscarPorCodigoU(codigo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: "+ codigo));
        model.addAttribute("usuario", usuario);
        return "usuario/formulario";
    }
}
