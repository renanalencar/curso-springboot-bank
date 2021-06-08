package com.accenture.academico.controller;

import javax.validation.Valid;

import com.accenture.academico.exception.ResourceNotFoundException;
import com.accenture.academico.model.Agencia;
import com.accenture.academico.repository.AgenciaRepository;

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

@RestController
public class AgenciaController {
    
    @Autowired
    private AgenciaRepository agenciaRepository;

    @GetMapping("/agencias")
    public Page<Agencia> getAllClientes(Pageable pageable) {
        return agenciaRepository.findAll(pageable);
    }

    @PostMapping("/agencias")
    public Agencia createAgencia(@Valid @RequestBody Agencia agencia) {
        return agenciaRepository.save(agencia);
    }

    @PutMapping("/agencias/{idAgencia}")
    public Agencia udapteAgencia(@PathVariable Long idAgencia, @Valid @RequestBody Agencia agenciaRequest) {
        return agenciaRepository.findById(idAgencia).map(agencia -> {
            agencia.setNomeAgencia(agenciaRequest.getNomeAgencia());
            agencia.setEndereco(agenciaRequest.getEndereco());
            agencia.setTelefone(agenciaRequest.getTelefone());
            return agenciaRepository.save(agencia);
        }).orElseThrow(() -> new ResourceNotFoundException("idAgencia" + idAgencia + "not found"));
    }

    @DeleteMapping("/agencias/{idAgencia}")
    public ResponseEntity<?> deleteAgencia(@PathVariable Long idAgencia) {
        return agenciaRepository.findById(idAgencia).map( agencia -> {
            agenciaRepository.delete(agencia);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("idAgencia" + idAgencia + "not found"));
    }
}
