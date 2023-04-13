package com.br.miniautorizador.entrypoint.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotBlank(message = "Número cartão não deve ser nulo")
    @Size(min = 16, message = "O cartão deve ter 16 números")
    @Size(max = 16, message = "O cartão deve ter 16 números")
    @Schema(description = "Número do cartão", example = "6549873025634501", required = true)
    @JsonProperty("numeroCartao")
    private String numberCard;

    @NotBlank(message = "Senha cartão não deve ser nulo")
    @Schema(description = "Senha do cartão", example = "1234", required = true)
    @JsonProperty("senhaCartao")
    private String password;

    @NotNull(message = "Valor da transação não deve ser nulo")
    @Schema(description = "Valor da transação", example = "113.34")
    @JsonProperty("valor")
    private BigDecimal amount;
}
