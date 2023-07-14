package br.com.banco.service.impl;

import br.com.banco.convert.TransferenciaConvert;
import br.com.banco.dto.TransferenciaResponse;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    @Override
    public Page<TransferenciaResponse> findAll(Pageable pageable) {
        Page<Transferencia> transferenciaPage = transferenciaRepository.findAll(pageable);
        List<TransferenciaResponse> transferenciaResponses = transferenciaPage.getContent().stream()
                .map(transferencia -> TransferenciaConvert.modelToDto(transferencia))
                .collect(Collectors.toList());

        return new PageImpl<>(transferenciaResponses, pageable, transferenciaPage.getTotalElements());
    }
}
