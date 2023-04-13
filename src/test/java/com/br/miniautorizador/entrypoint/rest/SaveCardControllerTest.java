package com.br.miniautorizador.entrypoint.rest;

import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.domain.usecase.SaveCardUseCase;
import com.br.miniautorizador.mock.factory.CardFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaveCardController.class)
class SaveCardControllerTest {

    @MockBean
    private SaveCardUseCase useCase;

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_ENDPOINT = "/cartoes";

    @Test
    void shouldTestSaveCardWithSuccess() throws Exception {
        var cardRequest = CardFactory.create();
        var cardResponse = CardFactory.createCardResponse();

        when(useCase.execute(eq(cardRequest))).thenReturn(cardResponse);

        mvc.perform(post(URL_ENDPOINT).contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(cardRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.senha").isNotEmpty())
                .andExpect(jsonPath("$.numeroCartao").isNotEmpty());
    }

    @Test
    void shouldTestSaveCardWithError() throws Exception {
        var cardRequest = CardFactory.create();
        when(useCase.execute(eq(cardRequest))).thenThrow(CardInvalidException.class);

        mvc.perform(post(URL_ENDPOINT).contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(cardRequest)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.senha").isNotEmpty())
                .andExpect(jsonPath("$.numeroCartao").isNotEmpty());
    }
}
