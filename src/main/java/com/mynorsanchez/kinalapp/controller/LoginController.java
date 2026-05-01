package com.mynorsanchez.kinalapp.controller;

import com.mynorsanchez.kinalapp.entity.LoginUser;
import com.mynorsanchez.kinalapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return isAdmin ? "dashboardAdmin" : "deshboardUser";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("loginUser", new LoginUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute LoginUser user, Model model) {
        try {
            loginService.register(user);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}