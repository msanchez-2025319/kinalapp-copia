package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Cliente;
import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.entity.Venta;
import com.mynorsanchez.kinalapp.service.IClientesService;
import com.mynorsanchez.kinalapp.service.IUsuarioService;
import com.mynorsanchez.kinalapp.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/ventas")
public class VentaViewController {

    @Autowired
    private IVentaService ventaService;

    @Autowired
    private IClientesService clienteService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listarTodo());
        return "ventas/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.listarActivos());
        model.addAttribute("usuarios", usuarioService.listarTodo());
        return "ventas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta,
                          @RequestParam("dpiCliente") String dpiCliente,
                          @RequestParam("codigoUsuario") Long codigoUsuario) {

        Cliente cliente = clienteService.buscarPorDPI(dpiCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Usuario usuario = usuarioService.buscarPorCodigoU(codigoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        venta.setCliente(cliente);
        venta.setUsuario(usuario);

        ventaService.guardar(venta);
        return "redirect:/vista/ventas";
    }

    @GetMapping("/editar/{codigo}")
    public String formularioEditar(@PathVariable Long codigo, Model model) {
        Venta venta = ventaService.buscarPorCodigoVenta(codigo)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteService.listarActivos());
        model.addAttribute("usuarios", usuarioService.listarTodo());
        return "ventas/formulario";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable Long codigo) {
        ventaService.eliminar(codigo);
        return "redirect:/vista/ventas";
    }
}