package br.com.banco.convert;

import br.com.banco.dto.TransferenciaResponse;
import br.com.banco.model.Transferencia;

public class TransferenciaConvert {

    public static TransferenciaResponse modelToDto(Transferencia transferencia) {
        return TransferenciaResponse.builder()
                .dataTransferencia(transferencia.getDataTransferencia())
                .valor(transferencia.getValor())
                .tipo(transferencia.getTipo())
                .nomeOperadorTransacao(transferencia.getNomeOperadorTransacao())
                .build();
    }
}
