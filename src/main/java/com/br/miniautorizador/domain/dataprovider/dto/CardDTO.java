package com.br.miniautorizador.domain.dataprovider.dto;

import com.br.miniautorizador.dataprovider.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private String number;
    private BigDecimal amount;
    private String password;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.number = card.getNumber();
        this.amount = card.getAmount();
        this.password = card.getPassword();
    }
}
