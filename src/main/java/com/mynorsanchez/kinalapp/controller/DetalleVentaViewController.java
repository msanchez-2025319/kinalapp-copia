package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.service.IDetalleVentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vista/detalle-venta")
public class DetalleVentaViewController {

    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaViewController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detalles", detalleVentaService.listarTodo());
        return "detalle-venta/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("detalle", new DetalleVenta());
        return "detalle-venta/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetalleVenta detalle, RedirectAttributes redirectAttributes) {
        try {
            detalleVentaService.guardar(detalle);
            redirectAttributes.addFlashAttribute("exito", "Detalle guardado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/detalle-venta/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al guardar el detalle.");
            return "redirect:/vista/detalle-venta/nuevo";
        }
        return "redirect:/vista/detalle-venta";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return detalleVentaService.buscarPorCodigoDetalleVenta(id)
                .map(detalle -> {
                    model.addAttribute("detalle", detalle);
                    return "detalle-venta/formulario";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Detalle no encontrado.");
                    return "redirect:/vista/detalle-venta";
                });
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute DetalleVenta detalle, RedirectAttributes redirectAttributes) {
        try {
            if (!detalleVentaService.existePorCodigoDetalleVenta(id)) {
                redirectAttributes.addFlashAttribute("error", "Detalle no encontrado.");
                return "redirect:/vista/detalle-venta";
            }
            detalleVentaService.actualizar(id, detalle);
            redirectAttributes.addFlashAttribute("exito", "Detalle actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/detalle-venta/editar/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al actualizar el detalle.");
            return "redirect:/vista/detalle-venta/editar/" + id;
        }
        return "redirect:/vista/detalle-venta";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (!detalleVentaService.existePorCodigoDetalleVenta(id)) {
                redirectAttributes.addFlashAttribute("error", "Detalle no encontrado.");
                return "redirect:/vista/detalle-venta";
            }
            detalleVentaService.eliminar(id);
            redirectAttributes.addFlashAttribute("exito", "Detalle eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el detalle.");
        }
        return "redirect:/vista/detalle-venta";
    }
}