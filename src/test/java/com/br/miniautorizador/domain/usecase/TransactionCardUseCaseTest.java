package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.BalanceInsufficientException;
import com.br.miniautorizador.common.exception.CardNonexistentTransactionException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import com.br.miniautorizador.domain.dataprovider.TransactionProvider;
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

    @Test
    void shouldReturnSuccessWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        doNothing().when(transactionProvider).execute(eq(transactionRequest));

        transactionCardUseCase.execute(transactionRequest);

        verify(transactionProvider).execute(eq(transactionRequest));
    }

    @Test
    void shouldReturnErrorCardNonexistentTransactionExceptionWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        doThrow(CardNonexistentTransactionException.class).when(transactionProvider).execute(eq(transactionRequest));

        assertThrows(CardNonexistentTransactionException.class, () -> transactionCardUseCase.execute(transactionRequest));
        verify(transactionProvider).execute(eq(transactionRequest));
    }

    @Test
    void shouldReturnErrorPasswordInvalidExceptionWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        doThrow(PasswordInvalidException.class).when(transactionProvider).execute(eq(transactionRequest));

        assertThrows(PasswordInvalidException.class, () -> transactionCardUseCase.execute(transactionRequest));
        verify(transactionProvider).execute(eq(transactionRequest));
    }

    @Test
    void shouldReturnErrorBalanceInsufficientExceptionWhenExecuteTransaction(){
        var transactionRequest = TransactionFactory.create();
        doThrow(BalanceInsufficientException.class).when(transactionProvider).execute(eq(transactionRequest));

        assertThrows(BalanceInsufficientException.class, () -> transactionCardUseCase.execute(transactionRequest));
        verify(transactionProvider).execute(eq(transactionRequest));
    }
}
