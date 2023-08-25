package com.soulcode.goserviceapp.controller;

import com.soulcode.goserviceapp.domain.Servico;
import com.soulcode.goserviceapp.domain.Usuario;
import com.soulcode.goserviceapp.service.ServicoService;
import com.soulcode.goserviceapp.service.UsuarioService;
import com.soulcode.goserviceapp.service.exception.UsuarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdministradorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicoService servicoService;

    @GetMapping(value = "/servicos")
    public String servico() {
        return "servicosAdmin";
    }

    @PostMapping(value = "/servicos")
    public String createService(Servico servico, RedirectAttributes attributes){
        try{
            servicoService.createServico(servico);
            attributes.addFlashAttribute("successMessage", "Novo serviço adicionado.");
        }catch (Exception ex){
            attributes.addFlashAttribute("errorMessage", "Erro ao adicionar novo servico.");
        }

        return "redirect:/admin/servicos";
    }

    @GetMapping(value = "/usuarios")
    public ModelAndView usuarios() {
        ModelAndView mv = new ModelAndView("usuariosAdmin");
        try{
            List <Usuario> usuarios = usuarioService.findAll();
            mv.addObject("usuarios", usuarios);
        } catch (Exception ex){
            mv.addObject("errorMessage", "Erro ao buscar dados de usuário.");
        }
        return mv;
    }

    @PostMapping(value = "/usuarios")
    public String createUser(Usuario usuario, RedirectAttributes attributes) {
        try {
            usuarioService.createUser(usuario);
            attributes.addFlashAttribute("successMessage", "Usuário criado com sucesso.");
        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage", "Erro ao criar novo usuário.");
        }
        return "redirect:/admin/usuarios";
    }

    @PostMapping(value = "/usuarios/disable")
    public String disableUser(@RequestParam(name = "usuarioId") Long id, RedirectAttributes attributes){
        try{
            usuarioService.disableUser(id);
        }catch (UsuarioNaoEncontradoException ex){
            attributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex){
            attributes.addFlashAttribute("errorMessage", "Erro ao desabilitar usuário.");
        }
        return "redirect:/admin/usuarios";
    }
    @PostMapping(value = "/usuarios/enable")
    public String enableUser(@RequestParam(name = "usuarioId") Long id, RedirectAttributes attributes){
        try{
            usuarioService.enableUser(id);
        }catch (UsuarioNaoEncontradoException ex){
            attributes.addFlashAttribute("errorMessage", ex.getMessage());
        }catch (Exception ex){
            attributes.addFlashAttribute("errorMessage", "Erro ao desabilitar usuário.");
        }
        return "redirect:/admin/usuarios";
    }

}