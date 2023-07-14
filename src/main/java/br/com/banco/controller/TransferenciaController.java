package br.com.banco.controller;

import br.com.banco.service.impl.TransferenciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaServiceImpl transferenciaService;
}
