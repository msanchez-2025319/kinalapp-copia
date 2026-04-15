package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
