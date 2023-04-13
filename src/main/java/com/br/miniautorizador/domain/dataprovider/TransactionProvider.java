package com.br.miniautorizador.domain.dataprovider;

import com.br.miniautorizador.dataprovider.model.Card;

public interface TransactionProvider {

    void execute(Card transactionRequest);
}
