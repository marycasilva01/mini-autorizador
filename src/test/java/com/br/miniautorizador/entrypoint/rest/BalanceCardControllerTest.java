package com.br.miniautorizador.entrypoint.rest;

import com.br.miniautorizador.domain.usecase.BalanceCardUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(BalanceByNumberController.class)
class BalanceCardControllerTest {

    private static final String URL_ENDPOINT = "/cartoes/{numeroCartao}";
    private static String NUMBER = "1234098765431230";

    @MockBean
    private BalanceCardUseCase useCase;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnSuccessWhenGetCardByNumber() throws Exception {
        when(useCase.execute(NUMBER)).thenReturn(Optional.of(BigDecimal.valueOf(500L)));

        mvc.perform(get(URL_ENDPOINT, NUMBER))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void shouldReturnNotFoundWhenGetCardByNumber() throws Exception {
        when(useCase.execute(NUMBER)).thenReturn(Optional.empty());
        mvc.perform(get(URL_ENDPOINT, NUMBER))
                .andExpect(status().isNotFound());
    }

}
