package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.domain.dataprovider.TransactionProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionProviderImpl implements TransactionProvider {

    private final CardRepository repository;

    @Override
    public void execute(Card transaction) {
        repository.save(transaction);
    }
}
