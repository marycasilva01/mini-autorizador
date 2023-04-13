package com.br.miniautorizador.entrypoint.exception;

import lombok.Data;

@Data
public class CardExceptionDTO {

    private final String numeroCartao;
    private final String senha;
}
