package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.exception.CardNoFoundException;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.mock.factory.CardFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCardProviderImplTest {

    private static final String NUMBER_CARD = "2345678912345678";

    @InjectMocks
    private GetCardProviderImpl getCardProvider;

    @Mock
    private CardRepository cardRepository;

    @Test
    void shouldReturnSuccessWhenFindByNumber() {
        var optEntity = CardFactory.createCard();
        when(cardRepository.findByNumber(eq(NUMBER_CARD))).thenReturn(Optional.of(optEntity));

        var entity = getCardProvider.balance(NUMBER_CARD);
        assertTrue(entity.isPresent());
        verify(cardRepository).findByNumber(eq(NUMBER_CARD));
    }

    @Test
    void shouldReturnOptionalEmptyWhenFindByNumber() {
        when(cardRepository.findByNumber(eq(NUMBER_CARD))).thenReturn(Optional.empty());

        var entity = getCardProvider.balance(NUMBER_CARD);
        assertTrue(entity.isEmpty());
        verify(cardRepository).findByNumber(eq(NUMBER_CARD));
    }
}
