package com.br.miniautorizador.entrypoint.rest.model;

import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class CardResponse {

    private Long id;
    private String number;
    private String password;
    private BigDecimal amount;

    public CardResponse(CardDTO cardDTO) {
        this.id = cardDTO.getId();
        this.number = cardDTO.getNumber();
        this.amount = cardDTO.getAmount();
        this.password = cardDTO.getPassword();
    }
}
