package br.com.banco.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaResponse {

    private LocalDateTime dataTransferencia;

    private Double valor;

    private String tipo;

    private String nomeOperadorTransacao;

}
