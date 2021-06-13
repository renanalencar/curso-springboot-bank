package com.accenture.academico;

import java.util.Date;

import com.accenture.academico.model.Agencia;
import com.accenture.academico.model.Cliente;
import com.accenture.academico.model.ContaCorrente;
import com.accenture.academico.model.Extrato;
import com.accenture.academico.model.enums.OperacaoEnum;
import com.accenture.academico.repository.AgenciaRepository;
import com.accenture.academico.repository.ClienteRepository;
import com.accenture.academico.repository.ContaCorrenteRepository;
import com.accenture.academico.repository.ExtratoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcademicoApplication implements CommandLineRunner {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	AgenciaRepository agenciaRepository;

	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	ExtratoRepository extratoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AcademicoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Limpar tabelas de banco de dados
		extratoRepository.deleteAllInBatch();
		contaCorrenteRepository.deleteAllInBatch();
		agenciaRepository.deleteAllInBatch();
		clienteRepository.deleteAllInBatch();

		//=========================================

		// Criar uma instância de cliente
		Cliente cliente = new Cliente("Renan Costa Alencar", "123.456.789-10", "(81) 98888-8888");

		// Criar uma instância de agência
		Agencia agencia = new Agencia("Ilha do Leite", "Avenida Agamenon Magalhães", "(81) 3333-3333", cliente);

		// Criar uma instância de conta corrente
		ContaCorrente contaCorrente = new ContaCorrente(agencia, "12345-6", 110.90, cliente);

		// Criar uma instância de extrato
		Date dataHora = new Date(System.currentTimeMillis());
		Extrato extrato = new Extrato(dataHora, OperacaoEnum.DEPOSITO, 55.25, contaCorrente);

		clienteRepository.save(cliente);
		agenciaRepository.save(agencia);
		contaCorrenteRepository.save(contaCorrente);
		extratoRepository.save(extrato);

		//=========================================

	}

}
