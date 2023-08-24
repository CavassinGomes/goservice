package com.soulcode.goserviceapp.controller;

import com.soulcode.goserviceapp.domain.Usuario;
import com.soulcode.goserviceapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin")
public class AdministradorController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/servicos")
    public String servico() {
        return "servicosAdmin";
    }

    @GetMapping(value = "/usuarios")
    public String usuarios() {
        return "usuariosAdmin";
    }

    @PostMapping(value = "/usuarios")
    public String createUser(Usuario usuario, RedirectAttributes attributes) {
        try {
            usuarioService.createUser(usuario);
            attributes.addFlashAttribute("successMessage", "Usuário criado com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage", "Erro ao criar novo usuário");
        }
        return "redirect:/admin/usuarios";
    }
}