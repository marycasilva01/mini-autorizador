package com.br.miniautorizador.entrypoint.rest;

import com.br.miniautorizador.domain.usecase.GetCardUseCase;
import com.br.miniautorizador.entrypoint.rest.model.CardResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class GetCardByNumberController {

    private final GetCardUseCase useCase;

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Card Not Found")
            })
    @GetMapping("/{numeroCartao}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<CardResponse> execute(@PathVariable("numeroCartao") String numberCard){
        var cardResponse = useCase.execute(numberCard);
        return cardResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
