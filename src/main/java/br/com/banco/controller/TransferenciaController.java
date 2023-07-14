package br.com.banco.controller;

import br.com.banco.dto.TransferenciaResponse;
import br.com.banco.dto.TransferenciaRequestFilter;
import br.com.banco.exception.ContaNotFoundException;
import br.com.banco.service.impl.TransferenciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaServiceImpl transferenciaService;


    @GetMapping()
    public Page<TransferenciaResponse> findTransferencias(Pageable pageable){
        return transferenciaService.findAll(pageable);
    }

    @GetMapping("/account")
    public Page<TransferenciaResponse> findTransferenciasByContaId(TransferenciaRequestFilter transferenciaRequestFilter, Pageable pageable) throws ContaNotFoundException {
        return transferenciaService.findTransferenciasByContaId(transferenciaRequestFilter, pageable);
    }

    @GetMapping("/valor-total")
    public Double getValorTotal(TransferenciaRequestFilter transferenciaRequestFilter){
        return transferenciaService.getValorTotal(transferenciaRequestFilter);
    }
}
