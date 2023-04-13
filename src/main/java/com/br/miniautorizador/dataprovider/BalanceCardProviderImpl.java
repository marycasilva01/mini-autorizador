package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.domain.dataprovider.BalanceCardProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceCardProviderImpl implements BalanceCardProvider {

    private final CardRepository repository;

    @Override
    public Optional<BigDecimal> execute(String number) {
        var cardDTO = repository.findByNumber(number);
        return cardDTO.map(Card::getAmount);
    }
}
