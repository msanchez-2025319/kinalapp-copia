package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.Usuario;
import com.mynorsanchez.kinalapp.service.IUsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vista/usuarios")
public class UsuarioViewController {

    private final IUsuarioService usuarioService;

    public UsuarioViewController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodo());
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuario.setCodigoUsuario(null);
            usuarioService.guardar(usuario);
            redirectAttributes.addFlashAttribute("exito", "Usuario guardado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/usuarios/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al guardar el usuario.");
            return "redirect:/vista/usuarios/nuevo";
        }
        return "redirect:/vista/usuarios";
    }

    @GetMapping("/editar/{codigo}")
    public String formularioEditar(@PathVariable Long codigo, Model model,
                                   RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioService.buscarPorCodigoU(codigo)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
            model.addAttribute("usuario", usuario);
            return "usuarios/formulario";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/usuarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al cargar el usuario.");
            return "redirect:/vista/usuarios";
        }
    }

    @PostMapping("/actualizar/{codigo}")
    public String actualizar(@PathVariable Long codigo, @ModelAttribute Usuario usuario,
                             RedirectAttributes redirectAttributes) {
        try {
            if (!usuarioService.existePorCodigoU(codigo)) {
                redirectAttributes.addFlashAttribute("error", "Usuario no encontrado.");
                return "redirect:/vista/usuarios";
            }
            usuarioService.actualizar(codigo, usuario);
            redirectAttributes.addFlashAttribute("exito", "Usuario actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/vista/usuarios/editar/" + codigo;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al actualizar el usuario.");
            return "redirect:/vista/usuarios/editar/" + codigo;
        }
        return "redirect:/vista/usuarios";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable Long codigo, RedirectAttributes redirectAttributes) {
        try {
            if (!usuarioService.existePorCodigoU(codigo)) {
                redirectAttributes.addFlashAttribute("error", "Usuario no encontrado.");
                return "redirect:/vista/usuarios";
            }
            usuarioService.eliminar(codigo);
            redirectAttributes.addFlashAttribute("exito", "Usuario eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar: el usuario tiene registros asociados.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno al eliminar el usuario.");
        }
        return "redirect:/vista/usuarios";
    }
}