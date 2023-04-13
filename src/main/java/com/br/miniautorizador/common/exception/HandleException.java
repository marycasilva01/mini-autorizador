package com.br.miniautorizador.common.exception;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import org.springframework.stereotype.Component;

@Component
public class HandleException {

    public Object throwExceptionValidation(StatusTransactionEnum error) {
        switch (error){
            case CARTAO_EXISTENTE:
                throw new CardNonexistentBalanceException();
            case CARTAO_INEXISTENTE:
                throw new CardNonexistentTransactionException();
            case SENHA_INVALIDA:
                throw new PasswordInvalidException();
            case SALDO_INSUFICIENTE:
                throw new BalanceInsufficientException();
            default:
                return null;
        }
    }
}
