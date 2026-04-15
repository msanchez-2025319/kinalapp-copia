package com.mynorsanchez.kinalapp.controller;


import com.mynorsanchez.kinalapp.entity.Cliente;
import com.mynorsanchez.kinalapp.service.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/clientes")
public class ClienteViewController {

    @Autowired
    private IClientesService clientesService;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("clientes",clientesService.listarTodos());
        return "clientes/lista";
    }
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model){
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente){
        clientesService.guardar(cliente);
        return "redirect:/vista/clientes";

    }
    @GetMapping("/editar/{dpi}")
    public String formularioEditar(@PathVariable String dpi, Model model){
        Cliente cliente = clientesService.buscarPorDPI(dpi)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado:_" + dpi));
        model.addAttribute("cliente", cliente);
        return "clientes/formulario";
    }

    @GetMapping("/eliminar/{dpi}")
    public String eliminar(@PathVariable String dpi){
        clientesService.eliminar(dpi);
        return "redirect:/vista/clientes";
    }

}
