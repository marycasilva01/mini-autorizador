package com.br.miniautorizador.common.exception;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNonexistentBalanceException extends RuntimeException {

    public CardNonexistentBalanceException() {
        super(StatusTransactionEnum.CARTAO_INEXISTENTE.name());
    }
}
