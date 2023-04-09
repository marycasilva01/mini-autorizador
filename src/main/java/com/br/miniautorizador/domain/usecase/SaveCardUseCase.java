package com.br.miniautorizador.domain.usecase;

import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.domain.dataprovider.SaveCardProvider;
import com.br.miniautorizador.entrypoint.rest.model.CardRequest;
import com.br.miniautorizador.entrypoint.rest.model.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCardUseCase {

   private final SaveCardProvider cardProvider;

   public CardResponse execute (CardRequest request){
       checkIfNumberCardIsValid(request.getNumber());

       var cardDTO = cardProvider.execute(request);
       return new CardResponse(cardDTO);
   }

    private void checkIfNumberCardIsValid(String numberCard) {
        if(!numberCard.matches("^\\d+$"))
            throw new CardInvalidException();
    }

}
