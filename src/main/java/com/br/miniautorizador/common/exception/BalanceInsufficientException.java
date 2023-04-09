package com.br.miniautorizador.common.exception;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BalanceInsufficientException extends RuntimeException {

    public BalanceInsufficientException() {
        super(StatusTransactionEnum.SALDO_INSUFICIENTE.name());
    }
}