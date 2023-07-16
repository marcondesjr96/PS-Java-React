package br.com.banco.service;

import br.com.banco.dto.TransferenciaRequestFilter;
import br.com.banco.dto.TransferenciaResponse;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.impl.TransferenciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransferenciaServiceTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private TransferenciaServiceImpl transferenciaService;

    private Conta conta;
    private Transferencia transferencia;
    private Transferencia transferencia2;
    private TransferenciaRequestFilter allFilter;
    private TransferenciaRequestFilter filterWithNomeOperadorTransacao;
    private TransferenciaRequestFilter filterWithDatas;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        conta = new Conta();
        conta.setIdConta(1L);
        conta.setNomeResponsavel("Fulano");

        transferencia = Transferencia.builder()
                .contaId(conta.getIdConta())
                .id(1L)
                .valor(1000.00)
                .dataTransferencia(LocalDateTime.of(2020, 12, 23, 12, 14, 39))
                .tipo("TRANSFERENCIA")
                .nomeOperadorTransacao("Beltrano")
                .build();

        transferencia2 = Transferencia.builder()
                .contaId(conta.getIdConta())
                .id(2L)
                .valor(150.00)
                .dataTransferencia(LocalDateTime.of(2022, 10, 4, 10, 52, 12))
                .tipo("DEPOSITO")
                .build();


        allFilter = TransferenciaRequestFilter.builder()
                .contaId(conta.getIdConta())
                .dataInicial(LocalDateTime.of(2019, 1, 1, 0, 0, 0))
                .dataFinal(LocalDateTime.of(2021, 6, 30, 23, 59, 59))
                .nomeOperadorTransacao("Beltrano")
                .build();

        filterWithNomeOperadorTransacao = TransferenciaRequestFilter.builder()
                .contaId(conta.getIdConta())
                .nomeOperadorTransacao("Beltrano")
                .build();

        filterWithDatas = TransferenciaRequestFilter.builder()
                .contaId(conta.getIdConta())
                .dataInicial(LocalDateTime.of(2019, 1, 1, 0, 0, 0))
                .dataFinal(LocalDateTime.of(2021, 6, 30, 23, 59, 59))
                .build();

        pageable = Pageable.ofSize(10).withPage(0);
    }

    @Test
    public void testFindTransferenciasByContaIdWithAllFilter() {
        List<Transferencia> transferencias = Collections.singletonList(transferencia);

        when(contaRepository.findById(conta.getIdConta())).thenReturn(Optional.of(conta));
        when(transferenciaRepository.getTransferencias(allFilter, pageable)).thenReturn(new PageImpl<>(transferencias));

        Page<TransferenciaResponse> result = transferenciaService.findTransferenciasByContaId(allFilter, pageable);

        verify(contaRepository, times(1)).findById(allFilter.getContaId());
        verify(transferenciaRepository, times(1)).getTransferencias(allFilter, pageable);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testFindTransferenciasByContaIdWithFilterNomeOperador() {
        List<Transferencia> transferencias = Collections.singletonList(transferencia);

        when(contaRepository.findById(conta.getIdConta())).thenReturn(Optional.of(conta));
        when(transferenciaRepository.getTransferencias(filterWithNomeOperadorTransacao, pageable))
                .thenReturn(new PageImpl<>(transferencias));

        Page<TransferenciaResponse> result =
                transferenciaService.findTransferenciasByContaId(filterWithNomeOperadorTransacao, pageable);

        verify(contaRepository, times(1)).findById(filterWithNomeOperadorTransacao.getContaId());
        verify(transferenciaRepository, times(1))
                .getTransferencias(filterWithNomeOperadorTransacao, pageable);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testFindTransferenciasByContaIdWithFilterDatas() {
        List<Transferencia> transferencias = Collections.singletonList(transferencia);

        when(contaRepository.findById(conta.getIdConta())).thenReturn(Optional.of(conta));
        when(transferenciaRepository.getTransferencias(filterWithDatas, pageable))
                .thenReturn(new PageImpl<>(transferencias));

        Page<TransferenciaResponse> result =
                transferenciaService.findTransferenciasByContaId(filterWithDatas, pageable);

        verify(contaRepository, times(1)).findById(filterWithDatas.getContaId());
        verify(transferenciaRepository, times(1))
                .getTransferencias(filterWithDatas, pageable);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testGetValorTotal() {

        TransferenciaRequestFilter filter = new TransferenciaRequestFilter();

        when(transferenciaRepository.getValorTotal(filter)).thenReturn(transferencia.getValor() + transferencia2.getValor());

        Double result = transferenciaService.getValorTotal(filter);

        verify(transferenciaRepository, times(1)).getValorTotal(filter);

        assertEquals(1150.0, result);
    }
}
