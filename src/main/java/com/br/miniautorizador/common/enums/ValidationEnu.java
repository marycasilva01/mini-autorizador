//package com.br.miniautorizador.common.enums;
//
//
//import com.br.miniautorizador.common.exception.BalanceInsufficientException;
//import com.br.miniautorizador.common.exception.PasswordInvalidException;
//
//import java.math.BigDecimal;
//
//public enum ValidationEnu {
//
//    SALDO_INSUFICIENTE {
//        @Override
//        void checkBalance(BigDecimal balance, BigDecimal value) {
//            if ((balance.subtract(amountTransaction)).signum() == -1)
//                throw new BalanceInsufficientException();
//
//        }
//    },
//
//    SENHA_INVALIDA {
//        @Override
//        void checkBalance(BigDecimal balance, BigDecimal value) {
//            if (!passwordTransaction.equals(passwordCard))
//                throw new PasswordInvalidException();
//        }
//    },
//
//    CARTAO_INEXISTENTE{
//        @Override
//        void checkBalance(BigDecimal balance, BigDecimal value) {
//        }
//    };
//
//    abstract void checkBalance(BigDecimal balance, BigDecimal value);
//
//}
