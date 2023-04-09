package com.br.miniautorizador.entrypoint.exception;

import com.br.miniautorizador.common.exception.CardNonexistentTransactionException;
import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CardInvalidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ExceptionDTO businessExceptionHandler(CardInvalidException ex) {
        return new ExceptionDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(PasswordInvalidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ExceptionDTO businessExceptionHandler(PasswordInvalidException ex) {
        return new ExceptionDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(CardNonexistentTransactionException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ExceptionDTO businessExceptionHandler(CardNonexistentTransactionException ex) {
        return new ExceptionDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ExceptionDTO businessExceptionHandler(Exception ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return exceptionDTO;
    }
}
