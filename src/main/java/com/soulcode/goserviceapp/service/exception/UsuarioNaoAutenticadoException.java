package com.soulcode.goserviceapp.service.exception;

public class UsuarioNaoAutenticadoException extends RuntimeException{
    public UsuarioNaoAutenticadoException() {
        super("Usuário não está autenticado");
    }

    public UsuarioNaoAutenticadoException(String message) {
        super(message);
    }
}
