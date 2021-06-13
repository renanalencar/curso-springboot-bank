package com.accenture.academico.controller;

import javax.validation.Valid;

import com.accenture.academico.exception.ResourceNotFoundException;
import com.accenture.academico.model.Extrato;
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
public class ExtratoController {
    
    @Autowired
    private ExtratoRepository extratoRepository;

    @Autowired
    private ContaCorrenteRepository contaRepository;
    @ApiOperation(value = "Consulta todos os extratos de todas as contas corrrentes")
    @GetMapping("/extratos")
    public Page<Extrato> getAllExtratos(Pageable pageable) {
        return extratoRepository.findAll(pageable);
    }

    @ApiOperation(value = "Consulta todos os extratos de uma conta corrente")
    @GetMapping("/contas/{idContaCorrente}/extratos")
    public Page<Extrato> getAllExtratosByIdContaCorrente(@PathVariable (value = "idContaCorrente") Long idContaCorrente, Pageable pageable) {
        return contaRepository.findById(idContaCorrente).map(contaCorrente -> {
            // return extratoRepository.findByIdContaCorrente(idContaCorrente, pageable);
            return extratoRepository.findAll(pageable);
        }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
        
    }

    @ApiOperation(value = "Salva um novo extrato")
    @PostMapping("/contas/{idContaCorrente}/extratos")
    public Extrato createContaCorrente(@PathVariable (value = "idContaCorrente") Long idContaCorrente, @Valid @RequestBody Extrato extrato) {
        return contaRepository.findById(idContaCorrente).map(contaCorrente -> {
            // extrato.setContaCorrente(contaCorrente);
            return extratoRepository.save(extrato);
        }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
    }

    @ApiOperation(value = "Atualiza um extrato")
    @PutMapping("/extratos/{idExtrato}")
    public Extrato udapteContaCorrente(@PathVariable Long idExtrato, @Valid @RequestBody Extrato contaRequest) {
        return extratoRepository.findById(idExtrato).map(extrato -> {
            extrato.setDataHoraMovimento(contaRequest.getDataHoraMovimento());
            extrato.setOperacao(contaRequest.getOperacao());
            extrato.setValorOperacao(contaRequest.getValorOperacao());
            return extratoRepository.save(extrato);
        }).orElseThrow(() -> new ResourceNotFoundException("idExtrato" + idExtrato + "not found"));
    }

    @ApiOperation(value = "Apaga um extrato")
    @DeleteMapping("/extratos/{idExtrato}")
    public ResponseEntity<?> deleteContaCorrente(@PathVariable Long idExtrato) {
        return extratoRepository.findById(idExtrato).map( extrato -> {
            extratoRepository.delete(extrato);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("idExtrato" + idExtrato + "not found"));
    }
}
