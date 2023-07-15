package br.com.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataValidationException extends ResponseStatusException {
    public DataValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
