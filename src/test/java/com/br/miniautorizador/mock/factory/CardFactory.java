package com.br.miniautorizador.mock.factory;

import com.br.miniautorizador.dataprovider.model.Card;
import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import com.br.miniautorizador.entrypoint.rest.model.CardRequest;
import com.br.miniautorizador.entrypoint.rest.model.CardResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CardFactory {

    private static final String PASSWORD = "12345";
    private static final String NUMBER = "1234567812345678";
    private static final long ID = 12323L;

    public static CardDTO createCardDTO() {
        return CardDTO.builder()
                .amount(BigDecimal.valueOf(500))
                .id(ID)
                .password(PASSWORD)
                .number(NUMBER)
                .build();
    }

    public static CardResponse createCardResponse() {
        return new CardResponse(NUMBER, PASSWORD);
    }

    public static Card createCard() {
        return Card.builder()
                .amount(BigDecimal.valueOf(500L))
                .id(ID)
                .password(PASSWORD)
                .number(NUMBER)
                .build();
    }

    public static CardRequest create() {
        return new CardRequest(NUMBER, PASSWORD);
    }

    public static CardRequest create(String number) {
        return new CardRequest(number, PASSWORD);
    }
}
