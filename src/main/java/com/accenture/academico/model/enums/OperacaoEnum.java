package com.accenture.academico.model.enums;

public enum OperacaoEnum {
    SAQUE(1), 
    DEPOSITO(2), 
    TRANSFERENCIA(3);

    public int operacao;

    OperacaoEnum(int valor) {
        operacao = valor;
    }
    
    public int getOperacao() {
        return operacao;
    }
}
