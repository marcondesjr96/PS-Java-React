package br.com.banco.handler;

import br.com.banco.exception.ContaNotFoundException;
import br.com.banco.exception.ContaNotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContaNotFoundException.class)
    public ResponseEntity<ContaNotFoundExceptionDetails> handlerContaNotFoundException(ContaNotFoundException ex){
        return new ResponseEntity<>(ContaNotFoundExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(ex.getStatus().value())
                .details(ex.getMessage())
                .title("Not Found Exception, check the Documantion")
                .developerMessage(ex.getClass().getName())
                .build(), HttpStatus.NOT_FOUND);
    }
}
