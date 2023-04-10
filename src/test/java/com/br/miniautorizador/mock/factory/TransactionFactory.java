package com.br.miniautorizador.mock.factory;

import com.br.miniautorizador.entrypoint.rest.model.TransactionRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionFactory {

    private static final String PASSWORD = "12345";
    private static final String NUMBER = "1234567812345678";

    public static TransactionRequest create(BigDecimal amount) {
        return create(PASSWORD, amount, NUMBER);
    }

    public static TransactionRequest create(String password) {
        return create(password, BigDecimal.TEN, NUMBER);
    }

    public static TransactionRequest create() {
        return create(PASSWORD, BigDecimal.TEN, NUMBER);
    }

    public static TransactionRequest create(String password, BigDecimal amount, String number) {
        return TransactionRequest.builder()
                .amount(amount)
                .numberCard(number)
                .password(password).build();
    }
}
