package br.com.banco.service.impl;

import br.com.banco.convert.TransferenciaConvert;
import br.com.banco.dto.TransferenciaResponse;
import br.com.banco.dto.TransferenciaRequestFilter;
import br.com.banco.exception.ContaNotFoundException;
import br.com.banco.exception.DataValidationException;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    private final ContaRepository contaRepository;

    @Override
    public Page<TransferenciaResponse> findAll(Pageable pageable) {
        Page<Transferencia> transferenciaPage = transferenciaRepository.findAll(pageable);
        return transferenciaPage.map(transferencia -> TransferenciaConvert.modelToDto(transferencia));
    }

    @Override
    public Page<TransferenciaResponse> findTransferenciasByContaId(TransferenciaRequestFilter filter, Pageable pageable) throws ContaNotFoundException {
        contaRepository.findById(filter.getContaId())
                .orElseThrow(() -> new ContaNotFoundException("Numero de conta não encontrado"));
        dateValidation(filter);
        Page<Transferencia> transferenciaPage = transferenciaRepository.getTransferencias(filter, pageable);
        return transferenciaPage.map(transferencia -> TransferenciaConvert.modelToDto(transferencia));
    }

    private static void dateValidation(TransferenciaRequestFilter filter) {
        if (filter.getDataInicial() != null && filter.getDataFinal() != null && filter.getDataInicial().isAfter(filter.getDataFinal())) {
            throw new DataValidationException("A data inicial não pode ser maior que a data final.");
        }
    }

    @Override
    public Double getValorTotal(TransferenciaRequestFilter filter) {
        return transferenciaRepository.getValorTotal(filter);
    }
}
