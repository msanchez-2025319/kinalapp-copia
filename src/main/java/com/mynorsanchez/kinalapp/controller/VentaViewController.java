package com.mynorsanchez.kinalapp.controller;


import com.mynorsanchez.kinalapp.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista/venta")
public class VentaViewController {

    @Autowired
    private IVentaService ventaService;
    
    @GetMapping
    public String listar(Model model){
        model.addAttribute("venta", ventaService.listarTodo());
        return "venta/lista";
    }
}
