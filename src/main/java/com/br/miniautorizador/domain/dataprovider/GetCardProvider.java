package com.br.miniautorizador.domain.dataprovider;

import com.br.miniautorizador.domain.dataprovider.dto.CardDTO;

import java.util.Optional;

public interface GetCardProvider {

    Optional<CardDTO> execute(String number);
}
