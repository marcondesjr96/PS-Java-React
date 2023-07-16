package br.com.banco.controller;

import br.com.banco.dto.TransferenciaRequestFilter;
import br.com.banco.dto.TransferenciaResponse;
import br.com.banco.exception.ContaNotFoundException;
import br.com.banco.service.impl.TransferenciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaServiceImpl transferenciaService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account")
    public Page<TransferenciaResponse> findTransferenciasByContaId(TransferenciaRequestFilter transferenciaRequestFilter, Pageable pageable) throws ContaNotFoundException {
        return transferenciaService.findTransferenciasByContaId(transferenciaRequestFilter, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/valor-total")
    public Double getValorTotal(TransferenciaRequestFilter transferenciaRequestFilter) {
        return transferenciaService.getValorTotal(transferenciaRequestFilter);
    }
}
