package com.soulcode.goserviceapp.controller;

import com.soulcode.goserviceapp.domain.Agendamento;
import com.soulcode.goserviceapp.domain.Chat;
import com.soulcode.goserviceapp.domain.Prestador;
import com.soulcode.goserviceapp.domain.Servico;
import com.soulcode.goserviceapp.service.AgendamentoService;
import com.soulcode.goserviceapp.service.ChatService;
import com.soulcode.goserviceapp.service.PrestadorService;
import com.soulcode.goserviceapp.service.ServicoService;
import com.soulcode.goserviceapp.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ChatService chatService;

    @GetMapping(value = "/dados")
    public ModelAndView dados(Authentication authentication) {
        ModelAndView mv = new ModelAndView("dadosPrestador");
        try {
            Prestador prestador = prestadorService.findAuthenticated(authentication);
            mv.addObject("prestador", prestador);
            List<Servico> especialidades = servicoService.findByPrestadorEmail(authentication.getName());
            mv.addObject("especialidades", especialidades);
            List<Servico> servicos = servicoService.findAll();
            mv.addObject("servicos", servicos);
        } catch (UsuarioNaoAutenticadoException | UsuarioNaoEncontradoException ex) {
            mv.addObject("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            mv.addObject("errorMessage", "Erro ao carregar dados do prestador.");
        }
        return mv;
    }

    @PostMapping(value = "/dados")
    public String editarDados(Prestador prestador, RedirectAttributes attributes) {
        try {
            prestadorService.update(prestador);
            attributes.addFlashAttribute("successMessage", "Dados alterados.");
        } catch (UsuarioNaoEncontradoException ex) {
            attributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            attributes.addFlashAttribute("errorMessage", "Erro ao alterar dados cadastrais.");
        }
        return "redirect:/prestador/dados";
    }

    @PostMapping(value = "/dados/especialidade/remover")
    public String removerEspecialidade(
            @RequestParam(name = "servicoId") Long id,
            Authentication authentication,
            RedirectAttributes attributes) {
        try {
            prestadorService.removeServicoPrestador(authentication, id);
            attributes.addFlashAttribute("successMessage", "Especialidade removida");
        } catch (UsuarioNaoAutenticadoException | UsuarioNaoEncontradoException | ServicoNaoEncontradoException ex) {
            attributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            attributes.addFlashAttribute("errorMessage", "Erro ao remover especialidade.");
        }
        return "redirect:/prestador/dados";
    }

    @PostMapping(value = "/dados/especialidade/adicionar")
    public String adicionarEspecialidade(
            @RequestParam(name = "servicoId") Long id,
            Authentication authentication,
            RedirectAttributes attributes) {
        try {
            prestadorService.addServicoPrestador(authentication, id);
            attributes.addFlashAttribute("successMessage", "Especialidade adicionada.");
        } catch (UsuarioNaoAutenticadoException | UsuarioNaoEncontradoException | ServicoNaoEncontradoException ex) {
            attributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            attributes.addFlashAttribute("errorMessage", "Erro ao adicionar nova especialidade.");
        }
        return "redirect:/prestador/dados";
    }

    @GetMapping(value = "/agenda")
    public ModelAndView agenda(Authentication authentication, @RequestParam(name = "dataInicial", required = false) LocalDate dataInicial,
                               @RequestParam(name = "dataFinal", required = false) LocalDate dataFinal) {

        ModelAndView mv = new ModelAndView("agendaPrestador");
        try {
            if(dataFinal == null && dataInicial == null){
                List<Agendamento> agendamentos = agendamentoService.findByPrestador(authentication);
                mv.addObject("agendamentos", agendamentos);
            }else{
                List<Agendamento> agendamentos = agendamentoService.findAgendamentoPrestadorByData(authentication, dataInicial.toString(), dataFinal.toString());
                mv.addObject("agendamentos", agendamentos);
            }
        } catch (UsuarioNaoAutenticadoException | UsuarioNaoEncontradoException ex) {
            mv.addObject("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            mv.addObject("errorMessage", "Erro ao carregar dados de agendamentos.");
        }
        return mv;
    }

    @PostMapping(value = "/agenda/cancelar")
    public String cancelarAgendamento(
            @RequestParam(name = "agendamentoId") Long agendamentoId,
            Authentication authentication,
            RedirectAttributes attributes) {
        try {
            agendamentoService.cancelAgendaPrestador(authentication, agendamentoId);
            attributes.addFlashAttribute("successMessage", "Agendamento cancelado.");
        } catch (UsuarioNaoAutenticadoException | UsuarioNaoEncontradoException |
                AgendamentoNaoEncontradoException | StatusAgendamentoImutavelException ex) {
            attributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            attributes.addFlashAttribute("errorMessage", "Erro ao cancelar agendamento.");
        }
        return "redirect:/prestador/agenda";
    }

    @PostMapping(value = "/agenda/confirmar")
    public String confirmarAgendamento(
            @RequestParam(name = "agendamentoId") Long agendamentoId,
            Authentication authentication,
            RedirectAttributes attributes) {
        try {
            agendamentoService.confirmAgenda(authentication, agendamentoId);
            attributes.addFlashAttribute("successMessage", "Agendamento confirmado.");
        } catch (UsuarioNaoAutenticadoException | UsuarioNaoEncontradoException |
                 AgendamentoNaoEncontradoException | StatusAgendamentoImutavelException ex) {
            attributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            attributes.addFlashAttribute("errorMessage", "Erro ao confirmar agendamento.");
        }
        return "redirect:/prestador/agenda";
    }

    @GetMapping(value = "/chat/{id}")
    public ModelAndView chatCliente(@PathVariable Long id, Authentication authentication){
        ModelAndView mv = new ModelAndView("chatPrestador");
        try {
            Agendamento agendamento = agendamentoService.findById(id);
            if(Objects.equals(agendamento.getPrestador().getEmail(), authentication.getName())){
            mv.addObject("agendamento", agendamento);
            List<Chat> chat = chatService.findByIdAgendamento(String.valueOf(id));
            mv.addObject("chatList", chat);
            }
        } catch (Exception ex) {
            mv.addObject("errorMessage", ex.getMessage());
        }
        return mv;
    }
    @PostMapping(value = "/chat/enviar")
    public String enviarMensagem(
            @RequestParam(name = "mensagem") String mensagem,
            @RequestParam(name = "agendamentoId") Long agendamentoId,
            RedirectAttributes attributes){
        Agendamento agendamento = agendamentoService.findById(agendamentoId);
        try {
            Chat chat = new Chat();
            chat.setMensagem(mensagem);
            chat.setRemetenteId(String.valueOf(agendamento.getCliente().getId()));
            chat.setDestinatarioId(String.valueOf(agendamento.getPrestador().getId()));
            chat.setAgendamentoId(String.valueOf(agendamento.getId()));
            chat.setClasseRemetente("Prestador");
            chatService.create(chat);
            return "redirect:/prestador/chat/" + agendamento.getId();
        } catch (Exception ex) {
            attributes.addFlashAttribute("errorMessage", "Erro ao enviar mensagem.");
            return "redirect:/prestador/chat/" + agendamento.getId();
        }
    }

}
