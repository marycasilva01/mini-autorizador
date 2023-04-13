package com.br.miniautorizador.entrypoint.rest;

import com.br.miniautorizador.common.exception.CardInvalidException;
import com.br.miniautorizador.common.exception.CardNonexistentBalanceException;
import com.br.miniautorizador.domain.usecase.SaveCardUseCase;
import com.br.miniautorizador.entrypoint.rest.model.CardRequest;
import com.br.miniautorizador.entrypoint.rest.model.CardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class SaveCardController {

    private final SaveCardUseCase useCase;

    @Operation(summary = "Create a new card")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Error operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardResponse.class)))
            })
    @PostMapping
    public ResponseEntity<CardResponse> execute(@RequestBody CardRequest cardRequest) {
        return new ResponseEntity<>(useCase.execute(cardRequest), CREATED);
    }
}
