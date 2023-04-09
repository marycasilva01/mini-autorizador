package com.br.miniautorizador.domain.dataprovider;

import com.br.miniautorizador.entrypoint.rest.model.TransactionRequest;

public interface TransactionProvider {

    void execute(TransactionRequest transactionRequest);
}
