package com.accenture.academico.controller;

import javax.validation.Valid;

import com.accenture.academico.exception.ResourceNotFoundException;
import com.accenture.academico.model.Extrato;
import com.accenture.academico.repository.ExtratoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtratoController {
    
    @Autowired
    private ExtratoRepository repository;

    // @GetMapping("/extratos")
    // public Page<Extrato> getAllContasCorrentes(Pageable pageable) {
    //     return repository.findAll(pageable);
    // }

    // @PostMapping("/extratos")
    // public Extrato createContaCorrente(@Valid @RequestBody Extrato extrato) {
    //     return repository.save(extrato);
    // }

    // @PutMapping("/extratos/{idExtrato}")
    // public Extrato udapteContaCorrente(@PathVariable Long idExtrato, @Valid @RequestBody Extrato contaRequest) {
    //     return repository.findById(idExtrato).map(extrato -> {
    //         extrato.setDataHoraMovimento(contaRequest.getDataHoraMovimento());
    //         extrato.setOperacao(contaRequest.getOperacao());
    //         extrato.setValorOperacao(contaRequest.getValorOperacao());
    //         return repository.save(extrato);
    //     }).orElseThrow(() -> new ResourceNotFoundException("idExtrato" + idExtrato + "not found"));
    // }

    // @DeleteMapping("/extratos/{idExtrato}")
    // public ResponseEntity<?> deleteContaCorrente(@PathVariable Long idExtrato) {
    //     return repository.findById(idExtrato).map( extrato -> {
    //         repository.delete(extrato);
    //         return ResponseEntity.ok().build();
    //     }).orElseThrow(() -> new ResourceNotFoundException("idExtrato" + idExtrato + "not found"));
    // }
}
