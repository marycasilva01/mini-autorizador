package com.br.miniautorizador.dataprovider;

import com.br.miniautorizador.common.exception.CardExistentException;
import com.br.miniautorizador.dataprovider.mapper.CardMapper;
import com.br.miniautorizador.dataprovider.repository.CardRepository;
import com.br.miniautorizador.domain.dataprovider.SaveCardProvider;
import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import com.br.miniautorizador.entrypoint.rest.model.CardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCardProviderImpl implements SaveCardProvider {

    private final CardRepository repository;
    private final CardMapper mapper;

    @Override
    public CardDTO execute(CardRequest save) {
        repository.findByNumber(save.getNumber()).ifPresent(card -> {
            throw new CardExistentException(save.getNumber(), save.getPassword());
        });

        var card = repository.save(mapper.toCard(save));
        return new CardDTO(card);
    }
}
