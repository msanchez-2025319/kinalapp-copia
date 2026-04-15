package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Producto;
import com.mynorsanchez.kinalapp.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista/producto")
public class ProductoViewController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productoService.listarTodo());
        return "productos/lista";
    }
     @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/formulario";
     }
     @GetMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto){
        productoService.guardar(producto);
        return "redirect:/vista/producto";
     }
}
