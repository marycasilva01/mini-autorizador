package com.br.miniautorizador.entrypoint.rest.model;

import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {

    @JsonProperty("numeroCartao")
    private String number;
    @JsonProperty("senha")
    private String password;

    public CardResponse(CardDTO cardDTO) {
        this.number = cardDTO.getNumber();
        this.password = cardDTO.getPassword();
    }
}
