package com.accenture.academico.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idCliente"))
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;

    @NotNull
    private String nomeCliente;

    @NotNull
    @Size(max = 14)
    private String clienteCpf;

    @NotNull
    private String clienteFone;

    public Cliente() {
    }

    public Cliente(String nomeCliente, String clienteCpf, String clienteFone) {
        this.nomeCliente = nomeCliente;
        this.clienteCpf = clienteCpf;
        this.clienteFone = clienteFone;
    }

    public Cliente(long idCliente, String nomeCliente, String clienteCpf, String clienteFone) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.clienteCpf = clienteCpf;
        this.clienteFone = clienteFone;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
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
