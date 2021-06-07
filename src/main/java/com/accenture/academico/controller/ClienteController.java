package com.accenture.academico.controller;

import com.accenture.academico.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
}
