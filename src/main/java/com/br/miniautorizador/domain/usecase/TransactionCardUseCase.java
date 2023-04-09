package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.domain.dataprovider.TransactionProvider;
import com.br.miniautorizador.entrypoint.rest.model.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCardUseCase {

    private final TransactionProvider cardProvider;

    public void execute(TransactionRequest transactionRequest){
        cardProvider.execute(transactionRequest);
    }
}