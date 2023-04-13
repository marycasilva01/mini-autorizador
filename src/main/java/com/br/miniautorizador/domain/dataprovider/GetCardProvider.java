package com.br.miniautorizador.domain.dataprovider;

import com.br.miniautorizador.dataprovider.model.Card;

import java.math.BigDecimal;
import java.util.Optional;

public interface GetCardProvider {

    Optional<BigDecimal> balance(String number);

    Card get(String number);
}
