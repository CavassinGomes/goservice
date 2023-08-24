package com.soulcode.goserviceapp.service;

import com.soulcode.goserviceapp.domain.Cliente;
import com.soulcode.goserviceapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository cllienteRepository;

    public Cliente findAuthenticaded(Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            Optional <Cliente> cliente = cllienteRepository.findByEmail(authentication.getName());
            if(cliente.isPresent()){
                return cliente.get();
            }else{
                throw new RuntimeException("Cliente não encontrado.");
            }
        }else{
            throw new RuntimeException("Cliente não autenticado");
        }
    }

    public Cliente findById(Long id){
        Optional<Cliente> cliente = cllienteRepository.findById(id);
        if(cliente.isPresent()){
            return cliente.get();
        }else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public Cliente update(Cliente cliente){
        Cliente updatedCliente = this.findById(cliente.getId());
        updatedCliente.setNome(cliente.getNome());
        updatedCliente.setEmail(cliente.getEmail());
        updatedCliente.setTelefone(cliente.getTelefone());
        updatedCliente.setCpf(cliente.getCpf());
        updatedCliente.setDataNascimento(cliente.getDataNascimento());
        return cllienteRepository.save(updatedCliente);
    }
}
