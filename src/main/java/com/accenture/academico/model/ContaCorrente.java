package com.accenture.academico.model;

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
public class ContaCorrente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idContaCorrente;


    private String contaCorrenteAgencia;

    @NotNull
    private int contaCorrenteNumero;

    private double contaCorrenteSaldo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente;
    
    public ContaCorrente(int idContaCorrente, String contaCorrenteAgencia, int contaCorrenteNumero,
            double contaCorrenteSaldo, Cliente cliente) {
        this.idContaCorrente = idContaCorrente;
        this.contaCorrenteAgencia = contaCorrenteAgencia;
        this.contaCorrenteNumero = contaCorrenteNumero;
        this.contaCorrenteSaldo = contaCorrenteSaldo;
        this.cliente = cliente;
    }

    public int getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(int idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public String getContaCorrenteAgencia() {
        return contaCorrenteAgencia;
    }

    public void setContaCorrenteAgencia(String contaCorrenteAgencia) {
        this.contaCorrenteAgencia = contaCorrenteAgencia;
    }

    public int getContaCorrenteNumero() {
        return contaCorrenteNumero;
    }

    public void setContaCorrenteNumero(int contaCorrenteNumero) {
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
