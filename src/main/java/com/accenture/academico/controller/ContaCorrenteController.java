package com.accenture.academico.controller;

import java.util.Date;

import javax.validation.Valid;

import com.accenture.academico.exception.ResourceNotFoundException;
import com.accenture.academico.model.Agencia;
import com.accenture.academico.model.Cliente;
import com.accenture.academico.model.ContaCorrente;
import com.accenture.academico.model.Extrato;
import com.accenture.academico.model.enums.OperacaoEnum;
import com.accenture.academico.repository.AgenciaRepository;
import com.accenture.academico.repository.ClienteRepository;
import com.accenture.academico.repository.ContaCorrenteRepository;
import com.accenture.academico.repository.ExtratoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private ExtratoRepository extratoRepository;

    @ApiOperation(value = "Consulta todas as contas correntes")
    @GetMapping("/contas")
    public Page<ContaCorrente> getAllContasCorrentes(Pageable pageable) {
        return contaCorrenteRepository.findAll(pageable);
    }

    @ApiOperation(value = "Consulta os dados da conta de um cliente")
    @GetMapping("clientes/{idCliente}/contas/{idContaCorrente}")
    public Page<ContaCorrente> getContaByIdClienteIdContaCorrente(@PathVariable(value = "idCliente") Long idCliente,
            @PathVariable(value = "idContaCorrente") Long idContaCorrente, Pageable pageable) {
        return contaCorrenteRepository.findAll(pageable);
    }

    @ApiOperation(value = "Salva uma nova conta corrente")
    @PostMapping("/contas")
    public ContaCorrente createContaCorrente(@Valid @RequestBody ContaCorrente contaCorrente) {
        Cliente cliente = contaCorrente.getCliente();
        Agencia agencia = contaCorrente.getContaCorrenteAgencia();

        return clienteRepository.findById(cliente.getIdCliente()).map(cli -> {
            contaCorrente.setCliente(cli);
            Agencia ag = agenciaRepository.findById(agencia.getIdAgencia()).orElseThrow();
            contaCorrente.setContaCorrenteAgencia(ag);
            return contaCorrenteRepository.save(contaCorrente);
        }).orElseThrow(() -> new ResourceNotFoundException("idCliente" + cliente.getIdCliente() + "not found"));
    }

    @ApiOperation(value = "Atualiza uma conta corrente")
    @PutMapping("/contas/{idContaCorrente}")
    public ContaCorrente udapteContaCorrente(@PathVariable Long idContaCorrente,
            @Valid @RequestBody ContaCorrente contaRequest) {
        return contaCorrenteRepository.findById(idContaCorrente).map(contaCorrente -> {
            contaCorrente.setContaCorrenteAgencia(contaRequest.getContaCorrenteAgencia());
            contaCorrente.setContaCorrenteNumero(contaRequest.getContaCorrenteNumero());
            contaCorrente.setContaCorrenteSaldo(contaRequest.getContaCorrenteSaldo());
            return contaCorrenteRepository.save(contaCorrente);
        }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
    }

    @ApiOperation(value = "Realiza o depósito em uma conta corrente")
    @PutMapping("/contas/{idContaCorrente}/deposito")
    public ContaCorrente depositoContaCorrente(@PathVariable(value = "idContaCorrente") Long idContaCorrente,
            @Valid @RequestBody ContaCorrente contaRequest) {
        return contaCorrenteRepository.findById(idContaCorrente).map(contaCorrente -> {
            double montante = contaRequest.getContaCorrenteSaldo();
            // recalcula o saldo da conta
            contaCorrente = recalcularSaldo(contaCorrente, OperacaoEnum.DEPOSITO, montante);
            // salva a data e hora da operacao
            Date movimentacao = new Date(System.currentTimeMillis());
            // instancia um novo extrato
            Extrato extrato = new Extrato(movimentacao, OperacaoEnum.DEPOSITO, contaCorrente.getContaCorrenteSaldo(),
                    contaCorrente);
            // salva o extrato
            extratoRepository.save(extrato);
            // atualiza a conta
            return contaCorrenteRepository.save(contaCorrente);

        }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
    }

    @ApiOperation(value = "Realiza o saque em uma conta corrente")
    @PutMapping("/contas/{idContaCorrente}/saque/")
    public ContaCorrente saqueContaCorrente(@PathVariable Long idContaCorrente,
            @Valid @RequestBody ContaCorrente contaRequest) {
        return contaCorrenteRepository.findById(idContaCorrente).map(contaCorrente -> {
            double montante = contaRequest.getContaCorrenteSaldo();
            // se o valor do saque for menor ou igual ao saldo atual
            if (contaCorrente.getContaCorrenteSaldo() >= montante) {
                // recalcula o saldo
                contaCorrente = recalcularSaldo(contaCorrente, OperacaoEnum.SAQUE, montante);
                // salva a data e hora da operacao
                Date movimentacao = new Date(System.currentTimeMillis());
                // instancia um novo extrato
                Extrato extrato = new Extrato(movimentacao, OperacaoEnum.SAQUE, montante, contaCorrente);
                // salva o exrato
                extratoRepository.save(extrato);
                // atualiza a conta
                return contaCorrenteRepository.save(contaCorrente);
            }

            return null;
        }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
    }

    @ApiOperation(value = "Realiza a transferência entre contas correntes")
    @PutMapping("/contas/{idCcDe}/transferencia/{idCcPara}")
    public ContaCorrente transferenciaEntreContas(@PathVariable(value = "idCcDe") Long idCcDe,
            @PathVariable(value = "idCcPara") Long idCcPara, @Valid @RequestBody ContaCorrente contaRequest) {
        return contaCorrenteRepository.findById(idCcPara).map(contaCorrentePara -> {
            // consulta a conta de origem da transferência
            ContaCorrente contaCorrenteDe = contaCorrenteRepository.findById(idCcDe).orElse(new ContaCorrente());
            // salva o valor solicitado da operacao
            double montante = contaRequest.getContaCorrenteSaldo();
            // se o valor solicitado for menor ou igual ao saldo da conta de origem
            if (contaCorrenteDe.getContaCorrenteSaldo() >= montante) {
                // salva a data e hora da operacao
                Date movimentacao = new Date(System.currentTimeMillis());
                // recalcula o saldo da conta de origem
                contaCorrenteDe = recalcularSaldo(contaCorrenteDe, OperacaoEnum.SAQUE, montante);
                // cria um novo extrato
                Extrato extrato = new Extrato(movimentacao, OperacaoEnum.TRANSFERENCIA,
                        contaCorrenteDe.getContaCorrenteSaldo(), contaCorrenteDe);
                // salva o extrato
                extratoRepository.save(extrato);
                // atualiza a conta de origem
                contaCorrenteDe.setContaCorrenteSaldo(contaCorrenteDe.getContaCorrenteSaldo());
                // recalcula o saldo da conta de destino
                contaCorrentePara = recalcularSaldo(contaCorrentePara, OperacaoEnum.DEPOSITO, montante);
                // cria um novo extrato
                extrato = new Extrato(movimentacao, OperacaoEnum.TRANSFERENCIA,
                        contaCorrentePara.getContaCorrenteSaldo(), contaCorrentePara);
                // salva o extrato
                extratoRepository.save(extrato);
                // atualiza a conta de destino
                return contaCorrenteRepository.save(contaCorrenteDe);
            }

            return null;
        }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrentePara" + idCcPara + "not found"));
    }

    @ApiOperation(value = "Apaga uma conta corrente")
    @DeleteMapping("/contas/{idContaCorrente}")
    public ResponseEntity<?> deleteContaCorrente(@PathVariable Long idContaCorrente) {
        return contaCorrenteRepository.findById(idContaCorrente).map(contaCorrente -> {
            contaCorrenteRepository.delete(contaCorrente);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
    }

    /***
     * Método que recalcula a o saldo da conta corrente de acordo com a operacao
     * 
     * @param contaCorrente
     * @param operacao
     * @param valor
     * @return
     */
    public ContaCorrente recalcularSaldo(ContaCorrente contaCorrente, OperacaoEnum operacao, double valor) {
        if (operacao == OperacaoEnum.DEPOSITO) {
            contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo() + valor);
            return contaCorrente;
        } else {
            contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo() - valor);
            return contaCorrente;
        }
    }
}
