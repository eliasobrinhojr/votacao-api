package com.eliasjr.sicredi.votacaoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class VotacaoApiApplication {

	private static final Logger logger = LogManager.getLogger(VotacaoApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VotacaoApiApplication.class, args);
		logger.info("Aplicacao iniciada com sucesso!");
	}

}
