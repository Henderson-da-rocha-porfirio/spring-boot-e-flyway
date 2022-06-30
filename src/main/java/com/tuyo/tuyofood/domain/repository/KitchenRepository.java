package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/* Esse repository instancia a implementação em tempo de execução. */
@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    /*List<Kitchen> consultarPorNome(String nome);*/
}
