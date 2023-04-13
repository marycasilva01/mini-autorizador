package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.BalanceInsufficientException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.domain.dataprovider.GetCardProvider;
import com.br.miniautorizador.domain.dataprovider.TransactionProvider;
import com.br.miniautorizador.entrypoint.rest.model.TransactionRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionCardUseCase {

    private final TransactionProvider transactionProvider;
    private final GetCardProvider getCardProvider;

    @Transactional
    public void execute(TransactionRequest transactionRequest) {
        var card = getCardProvider.get(transactionRequest.getNumberCard());
        checkPassword(transactionRequest.getPassword(), card.getPassword());
        checkBalanceCard(card.getAmount(), transactionRequest.getAmount());

        saveTransaction(card, transactionRequest);
    }

    private void checkBalanceCard(BigDecimal amount, BigDecimal amountTransaction) {
        if (amount.subtract(amountTransaction).signum() == -1) {
            throw new BalanceInsufficientException();
        }
    }

    private void checkPassword(String passwordTransaction, String passwordCard) {
        if (!passwordTransaction.equals(passwordCard)) {
            throw new PasswordInvalidException();
        }
    }

    private void saveTransaction(Card card, TransactionRequest transactionRequest) {
        card.setAmount(card.getAmount().subtract(transactionRequest.getAmount()));
        transactionProvider.execute(card);
    }
}
