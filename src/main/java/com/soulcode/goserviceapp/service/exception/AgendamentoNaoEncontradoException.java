package com.soulcode.goserviceapp.service.exception;

public class AgendamentoNaoEncontradoException extends RuntimeException{
    public AgendamentoNaoEncontradoException() {
        super("Agendamento não encontrado");
    }

    public AgendamentoNaoEncontradoException(String message) {
        super(message);
    }
}
