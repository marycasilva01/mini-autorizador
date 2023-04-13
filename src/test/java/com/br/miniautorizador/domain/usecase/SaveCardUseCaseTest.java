package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.common.exception.CardNonexistentBalanceException;
import com.br.miniautorizador.domain.dataprovider.SaveCardProvider;
import com.br.miniautorizador.mock.factory.CardFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveCardUseCaseTest {

    @InjectMocks
    private SaveCardUseCase saveCardUseCase;

    @Mock
    private SaveCardProvider saveCardProvider;

    @Test
    void shouldReturnSuccessWhenSaveCard() {
        var cardRequest = CardFactory.create();
        var cardDTO = CardFactory.createCardDTO();
        when(saveCardProvider.execute(eq(cardRequest))).thenReturn(cardDTO);

        var card = saveCardUseCase.execute(cardRequest);

        assertNotNull(card);
        assertEquals(card.getNumber(), cardRequest.getNumber());
        verify(saveCardProvider).execute(eq(cardRequest));
    }

    @Test
    void shouldReturnCardInvalidExceptionWhenSaveCard() {
        var cardRequest = CardFactory.create("213333qweee");
        assertThrows(CardInvalidException.class, () -> saveCardUseCase.execute(cardRequest));
    }

    @Test
    void shouldReturnErrorCardNonexistentBalanceExceptionWhenSaveCard() {
        var cardRequest = CardFactory.create();
        when(saveCardProvider.execute(eq(cardRequest))).thenThrow(CardNonexistentBalanceException.class);
        assertThrows(CardNonexistentBalanceException.class, () -> saveCardUseCase.execute(cardRequest));

        verify(saveCardProvider).execute(eq(cardRequest));
    }
}
