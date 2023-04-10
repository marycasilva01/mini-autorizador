package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.exception.BalanceInsufficientException;
import com.br.miniautorizador.common.exception.CardNonexistentTransactionException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.mock.factory.CardFactory;
import com.br.miniautorizador.mock.factory.TransactionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionProviderImplTest {

    @InjectMocks
    private TransactionProviderImpl transactionProvider;

    @Mock
    private CardRepository cardRepository;

    @Test
    void shouldReturnSuccessWhenTransaction() {
        var request = TransactionFactory.create();
        var entity = CardFactory.createCard();
        when(cardRepository.findByNumber(eq(request.getNumberCard()))).thenReturn(Optional.of(entity));
        when(cardRepository.save(any())).thenReturn(entity);

        transactionProvider.execute(request);
        verify(cardRepository).findByNumber(eq(request.getNumberCard()));
        verify(cardRepository).save(any());
    }

    @Test
    void shouldReturnErrorCardNonexistentTransactionExceptionWhenTransaction() {
        var request = TransactionFactory.create();
        when(cardRepository.findByNumber(eq(request.getNumberCard()))).thenReturn(Optional.empty());

        assertThrows(CardNonexistentTransactionException.class,
                () -> transactionProvider.execute(request));

        verify(cardRepository, never()).save(any());
        verify(cardRepository).findByNumber(eq(request.getNumberCard()));

    }

    @Test
    void shouldReturnErrorPasswordInvalidExceptionWhenTransaction() {
        var request = TransactionFactory.create("0000");
        var entity = CardFactory.createCard();
        when(cardRepository.findByNumber(eq(request.getNumberCard()))).thenReturn(Optional.of(entity));

        assertThrows(PasswordInvalidException.class,
                () -> transactionProvider.execute(request));

        verify(cardRepository, never()).save(any());
        verify(cardRepository).findByNumber(eq(request.getNumberCard()));
    }

    @Test
    void shouldReturnErrorBalanceInsufficientExceptionWhenTransaction() {
        var request = TransactionFactory.create(BigDecimal.valueOf(510L));
        var entity = CardFactory.createCard();
        when(cardRepository.findByNumber(eq(request.getNumberCard()))).thenReturn(Optional.of(entity));

        assertThrows(BalanceInsufficientException.class,
                () -> transactionProvider.execute(request));

        verify(cardRepository, never()).save(any());
        verify(cardRepository).findByNumber(eq(request.getNumberCard()));
    }
}
