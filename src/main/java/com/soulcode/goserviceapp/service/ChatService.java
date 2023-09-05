package com.soulcode.goserviceapp.service;

import com.soulcode.goserviceapp.domain.Chat;
import com.soulcode.goserviceapp.repository.ChatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    public Chat create(Chat chat){
        return chatRepository.save(chat);
    }

    public List<Chat> findAll(){
        return chatRepository.findAll();
    }

    public List<Chat> findByIdAgendamento(String id){
        List<Chat> chats = chatRepository.findByIdAgendamento(id);
        if (agendamentoService.findById(Long.valueOf(id)).getStatusAgendamento().getDescricao().equals("Confirmado") ||
            agendamentoService.findById(Long.valueOf(id)).getStatusAgendamento().getDescricao().equals("Concluido") ) {
            return chats;
        } else {
            throw new EntityNotFoundException("Chat não encontrado");
        }
    }
}
