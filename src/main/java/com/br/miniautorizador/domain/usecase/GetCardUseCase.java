package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.dataprovider.mapper.CardMapper;
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
        return provider.execute(number).map(CardResponse::new);
    }
}
