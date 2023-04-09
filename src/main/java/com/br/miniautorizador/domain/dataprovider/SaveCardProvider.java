package com.br.miniautorizador.domain.dataprovider;

import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;
import com.br.miniautorizador.domain.dataprovider.dto.SaveCardDTO;
import com.br.miniautorizador.entrypoint.rest.model.CardRequest;

public interface SaveCardProvider {

    CardDTO execute(CardRequest request);
}
