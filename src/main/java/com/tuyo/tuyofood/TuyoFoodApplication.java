package com.tuyo.tuyofood;

import com.tuyo.tuyofood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/* 1. @EnableJpaRepositories: ativa os repositórios. Apesar de já virem ativados por padrão, mas
* deve ser colocado para customizar, mudar o repositório base, na verdade
*  2. @EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class): dessa forma a gente
* substitui "SimpleJpaRepository" que estava como repositório-base padrão e torna "CustomJpaRepositoryImpl"(ele
* é um tipo SimpleJpaRepository, na verdade, uma especialização desse. */

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class TuyoFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuyoFoodApplication.class, args);
	}

}
