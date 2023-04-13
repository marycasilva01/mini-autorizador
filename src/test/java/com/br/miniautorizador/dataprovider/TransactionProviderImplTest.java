package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.exception.BalanceInsufficientException;
import com.br.miniautorizador.common.exception.CardNoFoundException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.mock.factory.CardFactory;
import com.br.miniautorizador.mock.factory.TransactionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionProviderImplTest {

    @InjectMocks
    private TransactionProviderImpl transactionProvider;

    @Mock
    private CardRepository cardRepository;

    @Test
    void shouldReturnSuccessWhenTransaction() {
        var entity = CardFactory.createCard();
        when(cardRepository.save(any())).thenReturn(entity);

        transactionProvider.execute(entity);
        verify(cardRepository).save(any());
    }
}
