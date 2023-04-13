package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import com.br.miniautorizador.common.exception.CardNonexistentTransactionException;
import com.br.miniautorizador.common.exception.HandleException;
import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.domain.dataprovider.TransactionProvider;
import com.br.miniautorizador.entrypoint.rest.model.TransactionRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionProviderImpl implements TransactionProvider {

    private final CardRepository repository;

    private final HandleException handleException;

    @Override
    @Transactional
    public void execute(TransactionRequest transactionRequest) {
        repository.findByNumber(transactionRequest.getNumberCard())
                .ifPresentOrElse(card -> {
                    checkPassword(transactionRequest.getPassword(), card.getPassword());
                    card.checkSetAmountCard(handleException, transactionRequest.getAmount());
                    saveTransaction(card, transactionRequest);
                }, () -> {
                    throw new CardNonexistentTransactionException();
                });
    }

    private void checkPassword(String passwordTransaction, String passwordCard) {
        var cardValid = passwordTransaction.equals(passwordCard) ? true :
                handleException.throwExceptionValidation(StatusTransactionEnum.SENHA_INVALIDA);
    }

    private void saveTransaction(Card card, TransactionRequest transactionRequest) {
        card.setAmount(card.getAmount().subtract(transactionRequest.getAmount()));
        repository.save(card);
    }
}
