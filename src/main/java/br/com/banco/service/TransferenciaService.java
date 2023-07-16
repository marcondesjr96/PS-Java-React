package br.com.banco.service;

import br.com.banco.dto.TransferenciaRequestFilter;
import br.com.banco.dto.TransferenciaResponse;
import br.com.banco.exception.ContaNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferenciaService {

    Page<TransferenciaResponse> findTransferenciasByContaId(TransferenciaRequestFilter transferenciaRequestFilter, Pageable pageable) throws ContaNotFoundException;

    Double getValorTotal(TransferenciaRequestFilter transferenciaRequestFilter);

}
