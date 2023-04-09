package com.br.miniautorizador.domain.dataprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveCardDTO {

    private String number;
    private String password;
}
