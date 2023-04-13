package com.br.miniautorizador.entrypoint.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {

    @JsonProperty("numeroCartao")
    private String number;
    @JsonProperty("senha")
    private String password;
}
