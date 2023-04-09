package com.br.miniautorizador.dataprovider.mapper;

import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import com.br.miniautorizador.domain.dataprovider.dto.SaveCardDTO;
import com.br.miniautorizador.entrypoint.rest.model.CardRequest;
import com.br.miniautorizador.entrypoint.rest.model.CardResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CardMapper {

    public Card toCard(CardRequest card){
        return Card.builder()
                .number(card.getNumber())
                .password(card.getPassword())
                .amount(BigDecimal.valueOf(500L))
                .build();
    }

}
