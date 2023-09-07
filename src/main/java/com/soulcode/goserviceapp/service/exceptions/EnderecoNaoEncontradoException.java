package com.soulcode.goserviceapp.service.exceptions;

public class EnderecoNaoEncontradoException extends RuntimeException{
    public EnderecoNaoEncontradoException() {
        super("Erro ao encontrar endereço.");
    }

    public EnderecoNaoEncontradoException(String message) {
        super(message);
    }
}
