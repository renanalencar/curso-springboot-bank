package com.accenture.academico.repository;

import com.accenture.academico.model.ContaCorrente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

}
