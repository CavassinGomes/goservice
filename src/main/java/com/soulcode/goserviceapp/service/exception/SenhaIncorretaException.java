package com.soulcode.goserviceapp.service.exception;

public class SenhaIncorretaException extends RuntimeException{
    public SenhaIncorretaException() {
        super("Senha incorreta.");
    }

    public SenhaIncorretaException(String message) {
        super(message);
    }
}
