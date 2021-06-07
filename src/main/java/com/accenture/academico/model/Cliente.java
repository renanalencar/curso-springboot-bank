package com.accenture.academico.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idCliente"))
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCliente;
    private String nomeCliente;
    private String clienteCpf;
    private String clienteFone;
    
    public Cliente(int idCliente, String nomeCliente, String clienteCpf, String clienteFone) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.clienteCpf = clienteCpf;
        this.clienteFone = clienteFone;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getClienteFone() {
        return clienteFone;
    }

    public void setClienteFone(String clienteFone) {
        this.clienteFone = clienteFone;
    }

    
}
