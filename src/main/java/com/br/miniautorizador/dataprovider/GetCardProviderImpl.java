package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.exception.CardNoFoundException;
import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.domain.dataprovider.GetCardProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCardProviderImpl implements GetCardProvider {

    private final CardRepository repository;

    @Override
    public Optional<BigDecimal> balance(String number) {
        return repository.findByNumber(number).map(Card::getAmount);
    }

    @Override
    public Card get(String number) {
        return repository.findByNumber(number).orElseThrow(CardNoFoundException::new);
    }
}
