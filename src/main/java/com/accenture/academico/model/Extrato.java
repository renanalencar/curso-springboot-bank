package com.accenture.academico.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.accenture.academico.model.enums.OperacaoEnum;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idExtrato"))
public class Extrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idExtrato;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraMovimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OperacaoEnum operacao;

    @NotNull
    private double valorOperacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_conta_corrente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ContaCorrente contaCorrente;

    public Extrato() {
    }

    public Extrato(long idExtrato, Date dataHoraMovimento, OperacaoEnum operacao, double valorOperacao,
            ContaCorrente contaCorrente) {
        this.idExtrato = idExtrato;
        this.dataHoraMovimento = dataHoraMovimento;
        this.operacao = operacao;
        this.valorOperacao = valorOperacao;
        this.contaCorrente = contaCorrente;
    }

    public Extrato(Date dataHoraMovimento, OperacaoEnum operacao, double valorOperacao,
            ContaCorrente contaCorrente) {
        this.dataHoraMovimento = dataHoraMovimento;
        this.operacao = operacao;
        this.valorOperacao = valorOperacao;
        this.contaCorrente = contaCorrente;
    }

    public long getIdExtrato() {
        return idExtrato;
    }

    public void setIdExtrato(long idExtrato) {
        this.idExtrato = idExtrato;
    }

    public Date getDataHoraMovimento() {
        return dataHoraMovimento;
    }

    public void setDataHoraMovimento(Date dataHoraMovimento) {
        this.dataHoraMovimento = dataHoraMovimento;
    }

    public OperacaoEnum getOperacao() {
        return operacao;
    }

    public void setOperacao(OperacaoEnum operacao) {
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
