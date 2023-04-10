package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.common.exception.CardNonexistentBalanceException;
import com.br.miniautorizador.domain.dataprovider.GetCardProvider;
import com.br.miniautorizador.mock.factory.CardFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCardUseCaseTest {

    private static final String CARD_NUMBER = "1234567812345678";
    @InjectMocks
    private GetCardUseCase getCardUseCase;

    @Mock
    private GetCardProvider getCardProvider;

    @Test
    void shouldReturnSuccessWhenFindCard() {
        var cardDTO = CardFactory.createCardDTO();
        when(getCardProvider.execute(eq(CARD_NUMBER))).thenReturn(Optional.of(cardDTO));

        var card = getCardUseCase.execute(CARD_NUMBER);
        assertTrue(card.isPresent());
        assertEquals(card.get().getNumber(), CARD_NUMBER);
        verify(getCardProvider).execute(eq(CARD_NUMBER));
    }

    @Test
    void shouldReturnCardInvalidExceptionWhenFindCard() {
        assertThrows(CardInvalidException.class, () -> getCardUseCase.execute("213333qweee"));
    }

    @Test
    void shouldReturnEmptyWhenFindCard() {
        when(getCardProvider.execute(eq(CARD_NUMBER))).thenReturn(Optional.empty());

        var card = getCardUseCase.execute(CARD_NUMBER);
        assertTrue(card.isEmpty());
        verify(getCardProvider).execute(eq(CARD_NUMBER));
    }
}
