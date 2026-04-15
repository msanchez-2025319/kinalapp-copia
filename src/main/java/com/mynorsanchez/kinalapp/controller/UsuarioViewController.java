package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
