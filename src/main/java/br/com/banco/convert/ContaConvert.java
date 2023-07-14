package br.com.banco.convert;

import br.com.banco.dto.ContaResponse;
import br.com.banco.model.Conta;

public class ContaConvert {

    public static ContaResponse modelToDto(Conta conta) {
        return ContaResponse.builder()
                .nomeResponsavel(conta.getNomeResponsavel())
                .build();
    }
}
