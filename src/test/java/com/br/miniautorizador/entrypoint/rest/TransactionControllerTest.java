package com.br.miniautorizador.entrypoint.rest;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import com.br.miniautorizador.common.exception.BalanceInsufficientException;
import com.br.miniautorizador.common.exception.CardNonexistentTransactionException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import com.br.miniautorizador.domain.usecase.BalanceCardUseCase;
import com.br.miniautorizador.domain.usecase.TransactionCardUseCase;
import com.br.miniautorizador.mock.factory.CardFactory;
import com.br.miniautorizador.mock.factory.TransactionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @MockBean
    private TransactionCardUseCase useCase;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_ENDPOINT = "/transacoes";

    @Test
    void shouldTestWithSuccessWhenTransaction() throws Exception {
        var transactionRequest = TransactionFactory.create();

        doNothing().when(useCase).execute(eq(transactionRequest));

        mvc.perform(post(URL_ENDPOINT).contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void shouldTestWithErrorCardNonexistentTransactionExceptionWhenTransaction() throws Exception {
        var transactionRequest = TransactionFactory.create();

        doThrow(CardNonexistentTransactionException.class).when(useCase).execute(eq(transactionRequest));

        mvc.perform(post(URL_ENDPOINT).contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value(StatusTransactionEnum.CARTAO_INEXISTENTE.name()));
    }

    @Test
    void shouldTestWithErrorPasswordInvalidExceptionWhenTransaction() throws Exception {
        var transactionRequest = TransactionFactory.create();

        doThrow(PasswordInvalidException.class).when(useCase).execute(eq(transactionRequest));

        mvc.perform(post(URL_ENDPOINT).contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value(StatusTransactionEnum.SENHA_INVALIDA.name()));
    }

    @Test
    void shouldTestWithErrorBalanceInsufficientExceptionWhenTransaction() throws Exception {
        var transactionRequest = TransactionFactory.create();
        doThrow(BalanceInsufficientException.class).when(useCase).execute(eq(transactionRequest));

        mvc.perform(post(URL_ENDPOINT).contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").value(StatusTransactionEnum.SALDO_INSUFICIENTE.name()));
    }
}
