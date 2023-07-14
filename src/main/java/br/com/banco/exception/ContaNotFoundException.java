package br.com.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaNotFoundException extends ResponseStatusException {

    public ContaNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, "Conta não existente!");
    }
}
