package com.accenture.academico.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idExtrato"))
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idExtrato;

    @NotNull
    private Date dataHoraMovimento;

    @NotNull
    private String operacao;
    
    private double valorOperacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idContaCorrente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ContaCorrente contaCorrente;
    
    public Extrato(int idExtrato, Date dataHoraMovimento, String operacao, double valorOperacao, ContaCorrente contaCorrente) {
        this.idExtrato = idExtrato;
        this.dataHoraMovimento = dataHoraMovimento;
        this.operacao = operacao;
        this.valorOperacao = valorOperacao;
        this.contaCorrente = contaCorrente;
    }

    public int getIdExtrato() {
        return idExtrato;
    }

    public void setIdExtrato(int idExtrato) {
        this.idExtrato = idExtrato;
    }

    public Date getDataHoraMovimento() {
        return dataHoraMovimento;
    }

    public void setDataHoraMovimento(Date dataHoraMovimento) {
        this.dataHoraMovimento = dataHoraMovimento;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    
}
