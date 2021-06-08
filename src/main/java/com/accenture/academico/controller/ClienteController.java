package com.accenture.academico.controller;

import javax.validation.Valid;

import com.accenture.academico.exception.ResourceNotFoundException;
import com.accenture.academico.model.Cliente;
import com.accenture.academico.repository.ClienteRepository;

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
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public Page<Cliente> getAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @PostMapping("/clientes")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/clientes/{idCliente}")
    public Cliente udapteCliente(@PathVariable Long idCliente, @Valid @RequestBody Cliente clienteRequest) {
        return clienteRepository.findById(idCliente).map(cliente -> {
            cliente.setNomeCliente(clienteRequest.getNomeCliente());
            cliente.setClienteCpf(clienteRequest.getClienteCpf());
            cliente.setClienteFone(clienteRequest.getClienteFone());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new ResourceNotFoundException("idCliente" + idCliente + "not found"));
    }

    @DeleteMapping("/clientes/{idCliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long idCliente) {
        return clienteRepository.findById(idCliente).map( cliente -> {
            clienteRepository.delete(cliente);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("idCliente" + idCliente + "not found"));
    }
}
