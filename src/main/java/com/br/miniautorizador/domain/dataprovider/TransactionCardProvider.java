package com.br.miniautorizador.domain.dataprovider;

import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import com.br.miniautorizador.entrypoint.rest.model.CardResponse;

public interface TransactionCardProvider {

    CardResponse execute(CardDTO cardDTO);
}
