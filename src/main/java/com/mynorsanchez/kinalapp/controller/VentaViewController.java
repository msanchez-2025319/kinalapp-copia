package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Cliente;
import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.entity.Venta;
import com.mynorsanchez.kinalapp.service.IClientesService;
import com.mynorsanchez.kinalapp.service.IUsuarioService;
import com.mynorsanchez.kinalapp.service.IVentaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vista/ventas")
public class VentaViewController {

    private final IVentaService ventaService;
    private final IClientesService clienteService;
    private final IUsuarioService usuarioService;

    public VentaViewController(IVentaService ventaService,
                               IClientesService clienteService,
                               IUsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

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
                          @RequestParam("codigoUsuario") Long codigoUsuario,
                          RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = clienteService.buscarPorDPI(dpiCliente)
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con DPI: " + dpiCliente));

            Usuario usuario = usuarioService.buscarPorCodigoU(codigoUsuario)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con codigo: " + codigoUsuario));

            venta.setCliente(cliente);
            venta.setUsuario(usuario);
            venta.setEstado(1L);

            ventaService.guardar(venta);
            redirectAttributes.addFlashAttribute("exito", "Venta guardada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/ventas/nuevo";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error de integridad al guardar la venta.");
            return "redirect:/vista/ventas/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al guardar la venta.");
            return "redirect:/vista/ventas/nuevo";
        }
        return "redirect:/vista/ventas";
    }

    @GetMapping("/editar/{codigo}")
    public String formularioEditar(@PathVariable Long codigo, Model model,
                                   RedirectAttributes redirectAttributes) {
        try {
            Venta venta = ventaService.buscarPorCodigoVenta(codigo)
                    .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada."));
            model.addAttribute("venta", venta);
            model.addAttribute("clientes", clienteService.listarActivos());
            model.addAttribute("usuarios", usuarioService.listarTodo());
            return "ventas/formulario";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/ventas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al cargar la venta.");
            return "redirect:/vista/ventas";
        }
    }

    @PostMapping("/actualizar/{codigo}")
    public String actualizar(@PathVariable Long codigo,
                             @ModelAttribute Venta venta,
                             @RequestParam("dpiCliente") String dpiCliente,
                             @RequestParam("codigoUsuario") Long codigoUsuario,
                             RedirectAttributes redirectAttributes) {
        try {
            if (!ventaService.existePorCodigoVenta(codigo)) {
                redirectAttributes.addFlashAttribute("error", "Venta no encontrada.");
                return "redirect:/vista/ventas";
            }

            Cliente cliente = clienteService.buscarPorDPI(dpiCliente)
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con DPI: " + dpiCliente));

            Usuario usuario = usuarioService.buscarPorCodigoU(codigoUsuario)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con codigo: " + codigoUsuario));

            venta.setCliente(cliente);
            venta.setUsuario(usuario);

            ventaService.actualizar(codigo, venta);
            redirectAttributes.addFlashAttribute("exito", "Venta actualizada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/ventas/editar/" + codigo;
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error de integridad al actualizar la venta.");
            return "redirect:/vista/ventas/editar/" + codigo;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al actualizar la venta.");
            return "redirect:/vista/ventas/editar/" + codigo;
        }
        return "redirect:/vista/ventas";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable Long codigo, RedirectAttributes redirectAttributes) {
        try {
            if (!ventaService.existePorCodigoVenta(codigo)) {
                redirectAttributes.addFlashAttribute("error", "Venta no encontrada.");
                return "redirect:/vista/ventas";
            }
            ventaService.eliminar(codigo);
            redirectAttributes.addFlashAttribute("exito", "Venta eliminada correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: la venta tiene detalles asociados.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al eliminar la venta.");
        }
        return "redirect:/vista/ventas";
    }
}