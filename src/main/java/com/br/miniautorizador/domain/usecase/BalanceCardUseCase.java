package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.domain.dataprovider.BalanceCardProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceCardUseCase {

    private final BalanceCardProvider provider;

    public Optional<BigDecimal> execute(String number){
        checkIfNumberCardIsValid(number);

        return provider.execute(number);
    }

    private void checkIfNumberCardIsValid(String numberCard) {
        if(!numberCard.matches("^\\d+$"))
            throw new CardInvalidException();
    }
}
