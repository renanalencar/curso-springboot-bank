package com.accenture.academico.controller;

import com.accenture.academico.repository.ExtratoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtratoController {
    
    @Autowired
    private ExtratoRepository repository;
}
