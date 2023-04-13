package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.BalanceInsufficientException;
import com.br.miniautorizador.common.exception.CardNoFoundException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import com.br.miniautorizador.domain.dataprovider.GetCardProvider;
import com.br.miniautorizador.domain.dataprovider.TransactionProvider;
import com.br.miniautorizador.mock.factory.CardFactory;
import com.br.miniautorizador.mock.factory.TransactionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionCardUseCaseTest {

    @InjectMocks
    private TransactionCardUseCase transactionCardUseCase;

    @Mock
    private TransactionProvider transactionProvider;

    @Mock
    private GetCardProvider getCardProvider;

    @Test
    void shouldReturnSuccessWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        var entity = CardFactory.createCard();
        when(getCardProvider.get(eq(transactionRequest.getNumberCard()))).thenReturn(entity);
        doNothing().when(transactionProvider).execute(eq(entity));

        transactionCardUseCase.execute(transactionRequest);

        verify(transactionProvider).execute(eq(entity));
    }

    @Test
    void shouldReturnErrorCardNonexistentTransactionExceptionWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        when(getCardProvider.get(eq(transactionRequest.getNumberCard()))).thenThrow(CardNoFoundException.class);

        assertThrows(CardNoFoundException.class, () -> transactionCardUseCase.execute(transactionRequest));
        verify(transactionProvider, never()).execute(any());
    }

    @Test
    void shouldReturnErrorPasswordInvalidExceptionWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        var card = CardFactory.createCard();
        when(getCardProvider.get(eq(transactionRequest.getNumberCard()))).thenReturn(card);
        doThrow(PasswordInvalidException.class).when(transactionProvider).execute(eq(card));

        assertThrows(PasswordInvalidException.class, () -> transactionCardUseCase.execute(transactionRequest));
        verify(transactionProvider).execute(eq(card));
        verify(getCardProvider).get(eq(transactionRequest.getNumberCard()));
    }

    @Test
    void shouldReturnErrorBalanceInsufficientExceptionWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        var card = CardFactory.createCard();
        when(getCardProvider.get(eq(transactionRequest.getNumberCard()))).thenReturn(card);
        doThrow(BalanceInsufficientException.class).when(transactionProvider).execute(eq(card));

        assertThrows(BalanceInsufficientException.class, () -> transactionCardUseCase.execute(transactionRequest));
        verify(transactionProvider).execute(eq(card));
    }
}
