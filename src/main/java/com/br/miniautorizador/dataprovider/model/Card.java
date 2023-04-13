package com.br.miniautorizador.dataprovider.model;

import com.br.miniautorizador.common.enums.StatusTransactionEnum;
import com.br.miniautorizador.common.exception.HandleException;
import jakarta.persistence.*;//TODO remover *
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Builder
@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "number")
    private String number;

    @Column(name = "amount")
    private BigDecimal amount;

    public void checkSetAmountCard(HandleException handleException, BigDecimal amountTransaction) {
        this.amount.add(this.amount.subtract(amountTransaction).signum() != -1 ? amountTransaction.subtract(amountTransaction) :
                (BigDecimal) handleException.throwExceptionValidation(StatusTransactionEnum.SALDO_INSUFICIENTE));
    }
}
