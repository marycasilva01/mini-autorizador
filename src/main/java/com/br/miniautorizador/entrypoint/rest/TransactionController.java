package com.br.miniautorizador.entrypoint.rest;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import com.br.miniautorizador.domain.usecase.TransactionCardUseCase;
import com.br.miniautorizador.entrypoint.rest.model.TransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/transacoes")
public class TransactionController {

    private final TransactionCardUseCase useCase;

    @Operation(summary = "Process a transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = StatusTransactionEnum.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(schema = @Schema(implementation = StatusTransactionEnum.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<StatusTransactionEnum> execute(@RequestBody @Valid TransactionRequest transactionRequest) {
        useCase.execute(transactionRequest);
        return new ResponseEntity<>(StatusTransactionEnum.OK, OK);
    }
}
