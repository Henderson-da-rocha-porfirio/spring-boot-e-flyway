package com.tuyo.tuyofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/* 1. @NoRepositoryBean: informa que esta interface não deve ser levada em conta afim de instanciação de um repositório
* pelo Spring Data JPA .
*  2. Esta é uma interface tipada.
*  3. Optional<T> buscarPrimeiro(): é a assinatura do método. */

@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> buscarPrimeiro();
}
