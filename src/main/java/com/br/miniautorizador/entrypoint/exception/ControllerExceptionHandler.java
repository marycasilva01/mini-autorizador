package com.br.miniautorizador.entrypoint.exception;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import com.br.miniautorizador.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CardInvalidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String businessExceptionHandler(CardInvalidException ex) {
        return "Cartão inválido.";
    }

    @ResponseBody
    @ExceptionHandler(PasswordInvalidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String businessExceptionHandler(PasswordInvalidException ex) {
        return StatusTransactionEnum.SENHA_INVALIDA.name();
    }

    @ResponseBody
    @ExceptionHandler(BalanceInsufficientException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String businessExceptionHandler(BalanceInsufficientException ex) {
        return StatusTransactionEnum.SALDO_INSUFICIENTE.name();
    }


    @ResponseBody
    @ExceptionHandler(CardNoFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String businessExceptionHandler(CardNoFoundException ex) {
        return StatusTransactionEnum.CARTAO_INEXISTENTE.name();
    }

    @ResponseBody
    @ExceptionHandler(CardExistentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    CardExceptionDTO businessExceptionHandler(CardExistentException ex) {
        return new CardExceptionDTO(ex.getNumeroCartao(), ex.getSenha());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String businessExceptionHandler(Exception ex) {
        return HttpStatus.INTERNAL_SERVER_ERROR.name();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String businessExceptionHandler(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return errors.toString();
    }
}
