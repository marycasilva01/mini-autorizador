package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.domain.dataprovider.GetCardProvider;
import com.br.miniautorizador.entrypoint.rest.model.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCardUseCase {

    private final GetCardProvider provider;

    public Optional<CardResponse> execute(String number){
        checkIfNumberCardIsValid(number);
        return provider.execute(number).map(CardResponse::new);
    }

    private void checkIfNumberCardIsValid(String numberCard) {
        if(!numberCard.matches("^\\d+$"))
            throw new CardInvalidException();
    }
}
