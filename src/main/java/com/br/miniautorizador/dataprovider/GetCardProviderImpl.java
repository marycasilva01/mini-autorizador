package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.domain.dataprovider.GetCardProvider;
import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCardProviderImpl implements GetCardProvider {

    private final CardRepository repository;

    @Override
    public Optional<CardDTO> execute(String number) {
        return repository.findByNumber(number).map(CardDTO::new);
    }
}
