package com.br.miniautorizador.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CardInvalidException extends RuntimeException {

    public CardInvalidException() {
        super("Cartão inválido.");
    }
}
