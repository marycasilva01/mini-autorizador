package com.br.miniautorizador.domain.dataprovider;

import java.math.BigDecimal;
import java.util.Optional;

public interface BalanceCardProvider {

    Optional<BigDecimal> execute(String number);
}
