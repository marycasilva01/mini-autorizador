package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.exception.CardNonexistentBalanceException;
import com.br.miniautorizador.dataprovider.mapper.CardMapper;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.mock.factory.CardFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveCardProviderImplTest {

    @InjectMocks
    private SaveCardProviderImpl saveCardProvider;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardMapper mapper;

    @Test
    void shouldReturnSuccessWhenSaveCard() {
        var request = CardFactory.create();
        var entity = CardFactory.createCard();
        when(cardRepository.findByNumber(eq(request.getNumber()))).thenReturn(Optional.empty());
        when(cardRepository.save(any())).thenReturn(entity);

        var saved = saveCardProvider.execute(request);

        assertEquals(entity.getId(), saved.getId());
        verify(cardRepository).findByNumber(eq(request.getNumber()));
    }

    @Test
    void shouldReturnThrowCardNonexistentBalanceExceptionWhenSaveCard() {
        var request = CardFactory.create();
        var entity = CardFactory.createCard();
        when(cardRepository.findByNumber(eq(request.getNumber()))).thenReturn(Optional.of(entity));

        assertThrows(CardNonexistentBalanceException.class,
                () -> saveCardProvider.execute(request));

        verify(cardRepository, never()).save(any());
    }
}
