package com.accenture.academico.repository;

import com.accenture.academico.model.Extrato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Integer> {
    
}
