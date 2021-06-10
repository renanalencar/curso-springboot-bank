package com.accenture.academico.controller;

import javax.validation.Valid;

import com.accenture.academico.exception.ResourceNotFoundException;
import com.accenture.academico.model.ContaCorrente;
import com.accenture.academico.repository.ContaCorrenteRepository;

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
public class ContaCorrenteController {
    
    @Autowired
    private ContaCorrenteRepository repository;

    // @GetMapping("/contas")
    // public Page<ContaCorrente> getAllContasCorrentes(Pageable pageable) {
    //     return repository.findAll(pageable);
    // }

    // @PostMapping("/contas")
    // public ContaCorrente createContaCorrente(@Valid @RequestBody ContaCorrente contaCorrente) {
    //     return repository.save(contaCorrente);
    // }

    // @PutMapping("/contas/{idContaCorrente}")
    // public ContaCorrente udapteContaCorrente(@PathVariable Long idContaCorrente, @Valid @RequestBody ContaCorrente contaRequest) {
    //     return repository.findById(idContaCorrente).map(contaCorrente -> {
    //         contaCorrente.setContaCorrenteAgencia(contaRequest.getContaCorrenteAgencia());
    //         contaCorrente.setContaCorrenteNumero(contaRequest.getContaCorrenteNumero());
    //         contaCorrente.setContaCorrenteSaldo(contaRequest.getContaCorrenteSaldo());
    //         return repository.save(contaCorrente);
    //     }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
    // }

    // @DeleteMapping("/contas/{idContaCorrente}")
    // public ResponseEntity<?> deleteContaCorrente(@PathVariable Long idContaCorrente) {
    //     return repository.findById(idContaCorrente).map( contaCorrente -> {
    //         repository.delete(contaCorrente);
    //         return ResponseEntity.ok().build();
    //     }).orElseThrow(() -> new ResourceNotFoundException("idContaCorrente" + idContaCorrente + "not found"));
    // }
}
