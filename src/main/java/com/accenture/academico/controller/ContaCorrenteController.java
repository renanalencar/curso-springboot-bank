package com.accenture.academico.controller;

import com.accenture.academico.repository.ContaCorrenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaCorrenteController {
    
    @Autowired
    private ContaCorrenteRepository repository;
}
