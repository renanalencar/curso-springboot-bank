package com.accenture.academico.model;

import java.io.Serializable;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idContaCorrente"))
public class ContaCorrente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idContaCorrente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_agencia", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Agencia contaCorrenteAgencia;

    @NotNull
    private String contaCorrenteNumero;

    @NotNull
    private double contaCorrenteSaldo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente;

    public ContaCorrente() {
    }

    public ContaCorrente(long idContaCorrente, Agencia contaCorrenteAgencia, String contaCorrenteNumero,
            double contaCorrenteSaldo, Cliente cliente) {
        this.idContaCorrente = idContaCorrente;
        this.contaCorrenteAgencia = contaCorrenteAgencia;
        this.contaCorrenteNumero = contaCorrenteNumero;
        this.contaCorrenteSaldo = contaCorrenteSaldo;
        this.cliente = cliente;
    }

    public ContaCorrente(Agencia contaCorrenteAgencia, String contaCorrenteNumero,
            double contaCorrenteSaldo, Cliente cliente) {
        this.contaCorrenteAgencia = contaCorrenteAgencia;
        this.contaCorrenteNumero = contaCorrenteNumero;
        this.contaCorrenteSaldo = contaCorrenteSaldo;
        this.cliente = cliente;
    }

    public long getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public Agencia getContaCorrenteAgencia() {
        return contaCorrenteAgencia;
    }

    public void setContaCorrenteAgencia(Agencia contaCorrenteAgencia) {
        this.contaCorrenteAgencia = contaCorrenteAgencia;
    }

    public String getContaCorrenteNumero() {
        return contaCorrenteNumero;
    }

    public void setContaCorrenteNumero(String contaCorrenteNumero) {
        this.contaCorrenteNumero = contaCorrenteNumero;
    }

    public double getContaCorrenteSaldo() {
        return contaCorrenteSaldo;
    }

    public void setContaCorrenteSaldo(double contaCorrenteSaldo) {
        this.contaCorrenteSaldo = contaCorrenteSaldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
