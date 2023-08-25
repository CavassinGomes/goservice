package com.soulcode.goserviceapp.service.exception;

public class ServicoNaoEncontradoException extends RuntimeException{
    public ServicoNaoEncontradoException() {
        super("Serviço não encontrado");
    }

    public ServicoNaoEncontradoException(String message) {
        super(message);
    }
}
