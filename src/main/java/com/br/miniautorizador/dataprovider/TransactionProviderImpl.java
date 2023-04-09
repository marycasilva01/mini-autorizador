package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.exception.CardNonexistentTransactionException;
import com.br.miniautorizador.common.exception.BalanceInsufficientException;
import com.br.miniautorizador.common.exception.PasswordInvalidException;
import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.domain.dataprovider.TransactionProvider;
import com.br.miniautorizador.entrypoint.rest.model.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionProviderImpl implements TransactionProvider {

    private final CardRepository repository;

    @Override
    public void execute(TransactionRequest transactionRequest) {
        repository.findByNumber(transactionRequest.getNumberCard())
                .ifPresentOrElse(card -> {
                    checkPassword(transactionRequest.getPassword(), card.getPassword());
                    checkAmountCard(card.getAmount(), transactionRequest.getAmount());
                    saveTransaction(card, transactionRequest);
                }, () -> {
                    throw new CardNonexistentTransactionException();
                });
    }

    private void checkPassword(String passwordTransaction, String passwordCard) {
        if (!passwordTransaction.equals(passwordCard))
            throw new PasswordInvalidException();
    }

    private void checkAmountCard(BigDecimal amountCard, BigDecimal amountTransaction) {
        if ((amountCard.subtract(amountTransaction)).signum() == -1)
            throw new BalanceInsufficientException();
    }

    private void saveTransaction(Card card, TransactionRequest transactionRequest) {
        card.setAmount(card.getAmount().subtract(transactionRequest.getAmount()));
        repository.save(card);
    }
}
