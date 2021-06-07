package com.accenture.academico.controller;

import com.accenture.academico.repository.AgenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgenciaController {
    
    @Autowired
    private AgenciaRepository agenciaRepository;
}
