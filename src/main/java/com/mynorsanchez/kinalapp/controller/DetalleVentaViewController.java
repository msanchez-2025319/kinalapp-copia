package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.service.IDetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista/detalle-venta")
public class DetalleVentaViewController {

    @Autowired
    private IDetalleVentaService detalleVentaService;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("detalle", detalleVentaService.listarTodo());
        return "detalle-venta/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model){
        model.addAttribute("detalle", new DetalleVenta());
        return "detalle-venta/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetalleVenta detalle){
        detalleVentaService.guardar(detalle);
        return "redirect:/vista/detalle-venta";
    }
}
